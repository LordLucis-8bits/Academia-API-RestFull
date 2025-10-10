package com.academia.Model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.Enum.TypeClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Enrollments")
public class EnrollmentModel {
    @Id
    private String id;
    private LocalDateTime registrationDate;
    private StudentsModel participantStudents;
    private TypeClass typeClass;
}
