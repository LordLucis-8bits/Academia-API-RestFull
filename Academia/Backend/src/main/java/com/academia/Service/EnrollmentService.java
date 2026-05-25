package com.academia.service;

import java.time.LocalDateTime;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.academia.dto.enrollment.EnrollmentResponseDTO;
import com.academia.enums.GymClassStatus;
import com.academia.model.EnrollmentModel;
import com.academia.model.GymClassModel;
import com.academia.model.StudentModel;
import com.academia.repository.EnrollmentRepository;
import com.academia.repository.GymClassRepository;
import com.academia.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final StudentRepository studentRepository;

    private final GymClassRepository gymClassRepository;

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentResponseDTO enrollStudent(@NonNull String studentId, @NonNull String classId) {
        StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        GymClassModel gymClass = gymClassRepository.findById(classId)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        //Verifica se o plano do estudante está ativo
        if (!student.isStudentPlanActive()) {
            throw new IllegalStateException("Inactive plan");
        }

        //Verifica se a aula está cheia
        long enrolledCount = enrollmentRepository.countByClassId(classId);
        if (enrolledCount >= gymClass.getStudentsLimit()) {
            throw new IllegalStateException("Class is full");
        }

        //Verifica se a aula esta com status disponível
        if (gymClass.getClassStatus() != GymClassStatus.AVAILABLE) {
            throw new IllegalStateException("Class is not available for enrollment");
        }

        //Verifica se o estudante já está matriculado na aula
        if (enrollmentRepository.existsByStudentIdAndClassId(studentId, classId)) {
            throw new IllegalStateException("Student already enrolled in this class");
        }

        EnrollmentModel enrollment = new EnrollmentModel();
        enrollment.setRegistrationDate(LocalDateTime.now());
        enrollment.setStudentId(studentId);
        enrollment.setClassId(classId);
    
        enrollmentRepository.save(enrollment);

        return new EnrollmentResponseDTO(enrollment);
    }
}
          