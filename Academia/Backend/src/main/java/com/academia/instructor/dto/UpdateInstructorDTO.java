package com.academia.instructor.dto;
import com.academia.shared.enums.TypeClass;

import lombok.Data;

@Data
public class UpdateInstructorDTO {
    private TypeClass specialty;
}
