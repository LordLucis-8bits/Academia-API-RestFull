package com.academia.model;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.PlanStatus;
import com.academia.enums.PlanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Students")
public class StudentModel extends UserModel {
    private PlanType planType;
    private LocalDate planStart;
    private LocalDate planEnd;
    private List<String> enrolledClassesIds;
    private PlanStatus planStatus;

    //Validar planos se esta ativo
    public boolean isStudentPlanActive() {
        return planStatus == PlanStatus.ACTIVE && (LocalDate.now().isBefore(planEnd) || LocalDate.now().isEqual(planEnd));
    }
}

