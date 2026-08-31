package com.academia.report;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.report.dto.StudentAttendanceDTO;
import com.academia.shared.enums.TypeClass;

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
    
    private String classId;

    private TypeClass nameClass;

    private String instructorId;

    private int totalStudents;

    private List<StudentAttendanceDTO> studentAttendances;

}