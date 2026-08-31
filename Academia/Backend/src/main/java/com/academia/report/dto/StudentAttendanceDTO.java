package com.academia.report.dto;

import com.academia.shared.UserModel;
import com.academia.student.StudentModel;

import lombok.Data;

@Data
public class StudentAttendanceDTO {
    private String id;
    private String name;
    private int age;

    //matrícula na aula = presença
    private boolean present;

    public StudentAttendanceDTO() {}

    public StudentAttendanceDTO(UserModel user, StudentModel student) {
       this.id = student.getId();
       this.name = user.getName();
       this.age = user.getAge();
       this.present = false;
    }

}
