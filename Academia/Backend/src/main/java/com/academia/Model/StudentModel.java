package com.academia.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.enums.PlanStatus;
import com.academia.enums.PlanType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Students")
public class StudentModel {
    
    @Id
    private String id;

    @NotNull
    private String userId; // Relacionamento com UserModel

    @NotNull
    private PlanType planType;

    @NotNull
    private LocalDate planStart;

    @NotNull
    private LocalDate planEnd;

    @NotNull
    private PlanStatus planStatus;

    //Validar planos se esta ativo
    public boolean isStudentPlanActive() {
        return planStatus == PlanStatus.ACTIVE && planEnd != null && (LocalDate.now().isBefore(planEnd) || LocalDate.now().isEqual(planEnd));
    }
}

