package com.academia.dto.instructor;

import com.academia.enums.TypeClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateInstructorDTO {

    @NotNull(message = "O id do usuário é obrigatório")
    private String userId;

    @NotBlank(message = "A especialidade é obrigatória")
    private TypeClass specialty;
}
