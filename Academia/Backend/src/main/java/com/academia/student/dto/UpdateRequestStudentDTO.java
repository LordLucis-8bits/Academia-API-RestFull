package com.academia.student.dto;

import com.academia.shared.dto.UpdateUserDTO;

import lombok.Data;

@Data
public class UpdateRequestStudentDTO {
    private UpdateUserDTO user;
    private UpdateStudentDTO student;
}