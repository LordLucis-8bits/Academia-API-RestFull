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
    public EnrollmentModel(String studentId, String classId) {}
    @Id
    private String id;
    private LocalDateTime registrationDate;
    private StudentModel participantStudents;
    private TypeClass typeClass;
}
