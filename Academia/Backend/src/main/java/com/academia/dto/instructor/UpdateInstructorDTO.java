package com.academia.dto.instructor;
import com.academia.enums.TypeClass;

import lombok.Data;

@Data
public class UpdateInstructorDTO {
    private TypeClass specialty;
}
