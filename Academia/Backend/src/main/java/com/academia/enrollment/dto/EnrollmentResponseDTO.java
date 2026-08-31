package com.academia.enrollment.dto;

import java.time.LocalDateTime;

import com.academia.enrollment.EnrollmentModel;

import lombok.Data;

@Data
public class EnrollmentResponseDTO {
    private String id;
    private LocalDateTime registrationDate;
    private String studentId;
    private String classId;

    public EnrollmentResponseDTO() {}

    public EnrollmentResponseDTO(EnrollmentModel enrollment) {
        this.id = enrollment.getId();
        this.registrationDate = enrollment.getRegistrationDate();
        this.studentId = enrollment.getStudentId();
        this.classId = enrollment.getClassId();
    }
}