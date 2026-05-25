package com.academia.dto.admin;

import com.academia.enums.UserType;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;
    private Integer age;
    private String email;
    private String password;
    private UserType role;
}
