package com.academia.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.enums.TypeClass;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Instructors")
public class InstructorModel {

    @Id
    private String id;

    @NotNull
    private String userId; //Relacionamento com UserModel
    
    @NotNull
    private TypeClass specialty;

    private List<String> ClassIds = new ArrayList<>();
}
