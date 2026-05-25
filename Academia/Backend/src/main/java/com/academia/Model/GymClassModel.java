package com.academia.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "GymClass")
public class GymClassModel {
    
    @Id
    private String id;

    @NotNull
    private TypeClass typeClass;

    @NotNull
    private LocalDateTime schedule;
    
    @NotNull
    private String instructorId;

    private int studentsLimit;

    private GymClassStatus classStatus;
}