package com.academia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academia.dto.instructor.InstructorResponseDTO;
import com.academia.dto.updateRequest.UpdateRequestInstructorDTO;
import com.academia.service.InstructorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    
    @PutMapping("/{userId}/{instructorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InstructorResponseDTO> updateInstructor(
        @PathVariable String userId, 
        @PathVariable String instructorId, 
        @RequestBody @Valid UpdateRequestInstructorDTO request) {
        InstructorResponseDTO response = instructorService.updateInstructor(userId, instructorId, request.getUser(), request.getInstructor());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInstructor(@PathVariable String id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    @ResponseStatus(HttpStatus.OK)
    public InstructorResponseDTO getInstructorById(@PathVariable String id) {
        return instructorService.getInstructorById(id);
    }

    //O instrutor logado vé suas informações
    @GetMapping("/me")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @ResponseStatus(HttpStatus.OK)
    public InstructorResponseDTO getCurrentInstructorInfo() {
        return instructorService.getCurrentInstructorInfo();
    }
}
