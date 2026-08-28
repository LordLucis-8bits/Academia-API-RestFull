package com.academia.dto.gymClass;

import java.time.LocalDateTime;

import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;
import com.academia.model.GymClassModel;

import lombok.Data;

@Data
public class GymClassResponseDTO {
    private String id;
    private TypeClass typeClass;
    private LocalDateTime schedule; 
    private String instructorId;
    private int studentsLimit;
    private GymClassStatus classStatus;

    public GymClassResponseDTO() {}

    public GymClassResponseDTO(GymClassModel model) {
        this.id = model.getId();
        this.typeClass = model.getTypeClass();
        this.schedule = model.getSchedule();
        this.instructorId = model.getInstructorId();
        this.studentsLimit = model.getStudentsLimit();
        this.classStatus = model.getClassStatus();
    }
}
