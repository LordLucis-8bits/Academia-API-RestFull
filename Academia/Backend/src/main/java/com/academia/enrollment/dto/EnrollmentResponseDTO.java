package com.academia.enrollment.dto;

import java.time.LocalDateTime;

import com.academia.enrollment.EnrollmentModel;
import com.academia.gymClass.GymClassModel;

import lombok.Data;

@Data
public class EnrollmentResponseDTO {
    private String id;
    private LocalDateTime registrationDate;
    private String studentId;
    private String classId;
    private String classType;
    private LocalDateTime classSchedule;

    public EnrollmentResponseDTO() {}

    public EnrollmentResponseDTO(EnrollmentModel enrollment, GymClassModel gymClass) {
        this.id = enrollment.getId();
        this.registrationDate = enrollment.getRegistrationDate();
        this.studentId = enrollment.getStudentId();
        this.classId = enrollment.getClassId();
        this.classType = gymClass.getTypeClass().toString();
        this.classSchedule = gymClass.getSchedule();
    }
}