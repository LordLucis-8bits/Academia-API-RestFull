package com.academia.dto.admin;

import com.academia.enums.PlanType;
import com.academia.enums.TypeClass;
import com.academia.enums.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Min(value = 1, message = "Idade mínima é 1")
    @Max(value = 120, message = "Idade máxima é 120")
    private int age;
    
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private UserType role;

    private PlanType planType; // Somente para STUDENT
    private TypeClass specialty; // Somente para INSTRUCTOR
}