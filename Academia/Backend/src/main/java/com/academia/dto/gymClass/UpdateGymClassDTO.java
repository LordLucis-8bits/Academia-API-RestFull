package com.academia.dto.gymClass;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdateGymClassDTO {
    private String typeClass;
    private LocalDateTime schedule; 
    private String instructorId;
    private int studentsLimit;
    private String classStatus;
}