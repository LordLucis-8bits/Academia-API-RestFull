package com.academia.dto.student;

import com.academia.enums.PlanStatus;
import com.academia.enums.PlanType;

import lombok.Data;

@Data
public class UpdateStudentDTO {
    private PlanType planType;
    private PlanStatus planStatus;
}
