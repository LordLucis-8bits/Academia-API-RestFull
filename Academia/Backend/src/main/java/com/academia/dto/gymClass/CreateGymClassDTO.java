package com.academia.dto.gymClass;

import java.time.LocalDateTime;

import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateGymClassDTO {

    //Validações Basicas
    @NotBlank(message = "O tipo da aula é obrigatório")
    private TypeClass typeClass;

    @NotNull(message = "O horário da aula é obrigatório")
    @Future(message = "O horário da aula deve ser no futuro")
    private LocalDateTime schedule;

    @NotNull(message = "O id do instrutor é obrigatório")
    private String instructorId;

    @NotNull(message = "O limite de alunos é obrigatório")
    @Min(value = 1, message = "Deve ter pelo menos 1 aluno")
    @Max(value = 100, message = "Máximo de 100 alunos permitido")
    private int studentsLimit;

    @NotNull(message = "O status da aula é obrigatório")
    private GymClassStatus classStatus;

}

