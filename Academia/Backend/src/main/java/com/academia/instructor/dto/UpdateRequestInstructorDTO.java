package com.academia.instructor.dto;

import com.academia.shared.dto.UpdateUserDTO;

import lombok.Data;

@Data
public class UpdateRequestInstructorDTO {
    private UpdateUserDTO user;
    private UpdateInstructorDTO instructor;
}
