package com.academia.shared.dto;

import com.academia.shared.UserModel;
import com.academia.shared.enums.UserType;

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
