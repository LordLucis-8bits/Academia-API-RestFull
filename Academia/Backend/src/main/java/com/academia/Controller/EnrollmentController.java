package com.academia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudent(@RequestParam String studentId, @RequestParam String classId) {
        try {
            enrollmentService.enrollStudent(studentId, classId);
            return ResponseEntity.status(202).body("Student Successfully enrolled");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
