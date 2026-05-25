package com.academia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.dto.gymClass.CreateGymClassDTO;
import com.academia.dto.gymClass.GymClassResponseDTO;
import com.academia.dto.gymClass.UpdateGymClassDTO;
import com.academia.model.UserModel;
import com.academia.service.GymClassService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class GymClassController {

    private final GymClassService gymClassService;
    
    //CRUD BASICO AULA
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GymClassResponseDTO> createClass(CreateGymClassDTO dto) {
        GymClassResponseDTO createdClass = gymClassService.createClass(dto);
        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN',)")
    public ResponseEntity<GymClassResponseDTO> updateClass(@PathVariable String id, UpdateGymClassDTO dto) {
        GymClassResponseDTO updateClass = gymClassService.updateClass(id, dto);
        return ResponseEntity.ok(updateClass);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClass(@PathVariable String id) {
        gymClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN, INSTRUCTOR, STUDENT')")
    public ResponseEntity<GymClassResponseDTO> getClassById(@PathVariable String id) {
        GymClassResponseDTO response = gymClassService.getClassById(id);
        return ResponseEntity.ok(response);
    }

    //REGRA DE NEGÓCIO
    //Iniciar uma aula
    @PatchMapping("/{classId}/start")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> startClass(@PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        gymClassService.startClass(classId, Instructor.getId());
        return ResponseEntity.ok().build();
    }

    //Finalizar uma aula
    @PatchMapping("/{classId}/finish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> finishClass(@PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        gymClassService.finishClass(classId, Instructor.getId());
        return ResponseEntity.ok().build(); 
    }
}
