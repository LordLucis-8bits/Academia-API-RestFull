package com.academia.Model;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reports")
public class ReportModel {
    @Id
    private String id;
    private ClassModel infoClass;
    private InstructorModel instructor;
    private List<StudentsModel> studentsParticipated;
}