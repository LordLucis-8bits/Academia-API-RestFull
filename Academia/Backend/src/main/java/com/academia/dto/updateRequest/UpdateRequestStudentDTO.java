package com.academia.dto.updateRequest;

import com.academia.dto.admin.UpdateUserDTO;
import com.academia.dto.student.UpdateStudentDTO;

import lombok.Data;

@Data
public class UpdateRequestStudentDTO {
    private UpdateUserDTO user;
    private UpdateStudentDTO student;
}