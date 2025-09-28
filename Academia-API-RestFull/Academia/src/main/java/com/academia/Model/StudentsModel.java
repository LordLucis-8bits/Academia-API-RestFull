package com.academia.Model;
import java.time.LocalDateTime;
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
    private LocalDateTime planStart;
    private LocalDateTime planEnd;
    private List<ClassModel> enrolledClasses;
    private PlanStatus planStatus;
}