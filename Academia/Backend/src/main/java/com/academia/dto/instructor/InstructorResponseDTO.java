package com.academia.dto.instructor;

import com.academia.enums.TypeClass;
import com.academia.model.InstructorModel;
import com.academia.model.UserModel;

import lombok.Data;

@Data
public class InstructorResponseDTO {
    private String id;

    private String userId;
    
    private String name;
    private int age;
    private String email;
    private TypeClass specialty;

    public InstructorResponseDTO() {}

    public InstructorResponseDTO(InstructorModel instructor, UserModel user) {
        this.id = instructor.getId();
        this.userId = instructor.getUserId();
        this.name = user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.specialty = instructor.getSpecialty();
    }
}
