package com.academia.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Classes")
public class GymClassModel {
    @Id
    private String gymClassId;
    private TypeClass typeClass; 
    private LocalDateTime schedule; 
    private String instructorId;
    private int studentsLimit;
    // Lista de IDs dos alunos inscritos na aula
    private List<String> enrolledStudents = new ArrayList<>();
    private GymClassStatus classStatus;
}