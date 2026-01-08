package com.academia.Model;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.Enum.TypeClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reports")
public class ReportModel {
    @Id
    private String Id;
    private TypeClass typeClass;
    private String classId;
    private String instructorId;
    private List<String> studentsId;
    private LocalDateTime TimeGeneration;
}