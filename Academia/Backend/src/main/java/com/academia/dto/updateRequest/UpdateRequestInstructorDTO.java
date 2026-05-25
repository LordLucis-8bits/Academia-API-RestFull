package com.academia.dto.updateRequest;

import com.academia.dto.admin.UpdateUserDTO;
import com.academia.dto.instructor.UpdateInstructorDTO;

import lombok.Data;

@Data
public class UpdateRequestInstructorDTO {
    private UpdateUserDTO user;
    private UpdateInstructorDTO instructor;
}
