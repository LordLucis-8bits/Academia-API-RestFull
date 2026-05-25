package com.academia.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.dto.report.StudentAttendanceDTO;
import com.academia.enums.TypeClass;

import jakarta.validation.constraints.NotNull;
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
    
    @NotNull
    private String classId;

    @NotNull
    private TypeClass nameClass;

    @NotNull
    private String instructorId;

    private int totalStudents;

    private List<StudentAttendanceDTO> studentAttendances;

}