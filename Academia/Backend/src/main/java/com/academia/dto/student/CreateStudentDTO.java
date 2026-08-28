package com.academia.dto.student;

import java.time.LocalDate;

import com.academia.enums.PlanStatus;
import com.academia.enums.PlanType;

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
