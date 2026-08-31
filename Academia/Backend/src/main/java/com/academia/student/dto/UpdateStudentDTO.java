package com.academia.student.dto;

import com.academia.shared.enums.PlanStatus;
import com.academia.shared.enums.PlanType;

import lombok.Data;

@Data
public class UpdateStudentDTO {
    private PlanType planType;
    private PlanStatus planStatus;
}
