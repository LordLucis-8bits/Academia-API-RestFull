package com.academia.gymClass;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.academia.shared.enums.GymClassStatus;
import com.academia.shared.enums.TypeClass;

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

    private TypeClass typeClass;

    private LocalDateTime schedule;

    private String instructorId;

    private Integer studentsLimit;

    private GymClassStatus classStatus;
}