package com.academia.student.dto;

import java.time.LocalDate;

import com.academia.shared.enums.PlanStatus;
import com.academia.shared.enums.PlanType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStudentDTO {

    @NotNull(message = "id do usuário é obrigatório")
    private String userId;

    @NotBlank(message = "O tipo de plano é obrigatório")
    private PlanType planType;

    private LocalDate planStart;

    @NotBlank(message = "O status do plano é obrigatório")
    private PlanStatus planStatus;
}
