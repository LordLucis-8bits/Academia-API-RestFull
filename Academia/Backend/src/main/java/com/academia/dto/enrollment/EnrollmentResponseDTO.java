package com.academia.dto.enrollment;

import java.time.LocalDateTime;

import com.academia.model.EnrollmentModel;

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