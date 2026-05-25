package com.academia.dto.report;

import java.util.List;

import com.academia.enums.TypeClass;
import com.academia.model.ReportModel;

import lombok.Data;

@Data
public class ClassAttendanceReportDTO {
    private String id;
    private String classId;
    private TypeClass className;
    private String instructorId;
    private int totalStudents;
    private List<StudentAttendanceDTO> studentAttendances;

    public ClassAttendanceReportDTO() {}

    public ClassAttendanceReportDTO(ReportModel report) {
        this.id = report.getId();
        this.classId = report.getClassId();
        this.className = report.getNameClass();
        this.instructorId = report.getInstructorId();
        this.totalStudents = report.getTotalStudents();
        this.studentAttendances = report.getStudentAttendances();
    }
}