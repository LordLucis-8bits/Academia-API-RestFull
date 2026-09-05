package com.academia.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.instructor.dto.InstructorResponseDTO;
import com.academia.shared.dto.CreateUserDTO;
import com.academia.shared.dto.UserResponseDTO;
import com.academia.student.dto.StudentResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    
    //Admin cria um novo usuario
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid CreateUserDTO request) {
        UserResponseDTO response = adminService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Lista alunos cadastrados
    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StudentResponseDTO>> listAllStudents() {
        List<StudentResponseDTO> response = adminService.listAllStudents();
        return ResponseEntity.ok(response);
    }

    //Lista instrutores cadastrados
    @GetMapping("/instructors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InstructorResponseDTO>> listAllInstructors() {
        List<InstructorResponseDTO> response = adminService.listAllInstructors();
        return ResponseEntity.ok(response);
    }
}