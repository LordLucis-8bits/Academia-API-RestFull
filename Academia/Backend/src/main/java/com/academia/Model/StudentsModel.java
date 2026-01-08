package com.academia.Model;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.Enum.PlanStatus;
import com.academia.Enum.PlanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Students")
public class StudentsModel extends UserModel {
    private PlanType planType;
    private LocalDate planStart;
    private LocalDate planEnd;
    private List<ClassModel> enrolledClassesIds;
    private PlanStatus planStatus;

    //Validar planos se esta ativo
    public boolean isStudentPlanActive() {
        return LocalDate.now().isBefore(planEnd) || LocalDate.now().isEqual(planEnd);
    }
}

