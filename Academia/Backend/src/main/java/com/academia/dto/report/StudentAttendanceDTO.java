package com.academia.dto.report;

import com.academia.model.StudentModel;
import com.academia.model.UserModel;

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
