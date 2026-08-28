package com.academia.dto.gymClass;

import java.time.LocalDateTime;

import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;

import lombok.Data;

@Data
public class UpdateGymClassDTO {
    private TypeClass typeClass;
    private LocalDateTime schedule; 
    private String instructorId;
    private Integer studentsLimit;
    private GymClassStatus classStatus;
}