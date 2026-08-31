package com.academia.gymClass.dto;

import java.time.LocalDateTime;

import com.academia.shared.enums.GymClassStatus;
import com.academia.shared.enums.TypeClass;

import lombok.Data;

@Data
public class UpdateGymClassDTO {
    private TypeClass typeClass;
    private LocalDateTime schedule; 
    private String instructorId;
    private Integer studentsLimit;
    private GymClassStatus classStatus;
}