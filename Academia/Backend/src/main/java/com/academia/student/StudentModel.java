package com.academia.student;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.shared.enums.PlanStatus;
import com.academia.shared.enums.PlanType;

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

    private String userId; // Relacionamento com UserModel

    private PlanType planType;

    private LocalDate planStart;

    private LocalDate planEnd;

    private PlanStatus planStatus;

    //Validar planos se esta ativo
    public boolean isStudentPlanActive() {
        return planStatus == PlanStatus.ACTIVE && planEnd != null && (LocalDate.now().isBefore(planEnd) || LocalDate.now().isEqual(planEnd));
    }
}

