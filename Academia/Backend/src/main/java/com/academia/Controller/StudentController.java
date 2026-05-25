package com.academia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.dto.gymClass.GymClassResponseDTO;
import com.academia.dto.student.StudentResponseDTO;
import com.academia.dto.updateRequest.UpdateRequestStudentDTO;
import com.academia.enums.PlanType;
import com.academia.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    
    //Atualizar os campos do aluno, incluindo os do usuarios
    @PutMapping("/{userId}/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable String userId, @PathVariable  String studentId, @RequestBody @Valid UpdateRequestStudentDTO request) {
        StudentResponseDTO response = studentService.updateStudent(userId, studentId, request.getUser(), request.getStudent());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    //Admin obtem informações do aluno por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', STUDENT')")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable String id) {
        StudentResponseDTO response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }

    //O aluno logado vé suas informações
    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentResponseDTO> getCurrentStudentInfo() {
        StudentResponseDTO response = studentService.getCurrentStudentInfo();
        return ResponseEntity.ok(response);
    }

    //REGRA DE NEGÓCIO
    //Listar aulas disponiveis para um aluno(ele só vera aulas disponiveis pra se matricular)
    @GetMapping("/classes/available")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<GymClassResponseDTO>> listAvailableClasses() {
        List<GymClassResponseDTO> response = studentService.listAvailableClasses();
        return ResponseEntity.ok(response);
    }

    //Verifica status plano
    @GetMapping("/{id}/plan/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<Boolean> checkPlanStatus(@PathVariable String id) {
        return ResponseEntity.ok(studentService.checkPlanStatus(id));
    }

    //Renovar plano (ADMIN)
    @GetMapping("/{id}/plan/renew")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> renewPlan(@PathVariable String id, @RequestParam PlanType newPlanType) {
        studentService.renewPlan(newPlanType);
        return ResponseEntity.noContent().build();
    }
}
