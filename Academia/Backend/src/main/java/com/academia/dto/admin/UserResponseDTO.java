package com.academia.dto.admin;

import com.academia.enums.UserType;
import com.academia.model.UserModel;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private int age;
    private UserType role;

    public UserResponseDTO() {}

    public UserResponseDTO(UserModel user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.role = user.getRole();
    }
}
