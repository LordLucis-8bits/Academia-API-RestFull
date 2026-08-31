package com.academia.student.dto;

import java.time.LocalDate;

import com.academia.shared.UserModel;
import com.academia.shared.enums.PlanStatus;
import com.academia.shared.enums.PlanType;
import com.academia.student.StudentModel;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private String id;

    private String userId;

    private String name;
    private int age;
    private String email;
    private PlanType planType;
    private LocalDate planStart;
    private LocalDate planEnd;
    private PlanStatus planStatus;

    public StudentResponseDTO() {}

    public StudentResponseDTO(StudentModel student, UserModel user) {
        this.id = student.getId();
        this.userId = student.getUserId();
        this.name = user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.planType = student.getPlanType();
        this.planStart = student.getPlanStart();
        this.planEnd = student.getPlanEnd();
        this.planStatus = student.getPlanStatus();
    }
}
