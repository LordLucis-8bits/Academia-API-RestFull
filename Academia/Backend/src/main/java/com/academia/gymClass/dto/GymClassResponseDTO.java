package com.academia.gymClass.dto;

import java.time.LocalDateTime;

import com.academia.gymClass.GymClassModel;
import com.academia.shared.enums.GymClassStatus;
import com.academia.shared.enums.TypeClass;

import lombok.Data;

@Data
public class GymClassResponseDTO {
    private String id;
    private TypeClass typeClass;
    private LocalDateTime schedule; 
    private String instructorId;
    private int studentsLimit;
    private GymClassStatus classStatus;
    private long enrolledCount;

    public GymClassResponseDTO() {}

    public GymClassResponseDTO(GymClassModel model) {
        this.id = model.getId();
        this.typeClass = model.getTypeClass();
        this.schedule = model.getSchedule();
        this.instructorId = model.getInstructorId();
        this.studentsLimit = model.getStudentsLimit();
        this.classStatus = model.getClassStatus();
    }

    public GymClassResponseDTO(GymClassModel model, long enrolledCount) {
        this.id = model.getId();
        this.typeClass = model.getTypeClass();
        this.schedule = model.getSchedule();
        this.instructorId = model.getInstructorId();
        this.studentsLimit = model.getStudentsLimit();
        this.classStatus = model.getClassStatus();
        this.enrolledCount = enrolledCount;
    }

}
