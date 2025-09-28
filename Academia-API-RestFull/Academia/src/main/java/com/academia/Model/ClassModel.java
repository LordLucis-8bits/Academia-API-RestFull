package com.academia.Model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.Enum.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classes")
public class ClassModel {
    @Id
    private String id;
    private String typeClass;
    private LocalDateTime schedule;
    private IstructorModel instructor;
    private int studentsLimit;
    private PlanStatus planStatus;
}