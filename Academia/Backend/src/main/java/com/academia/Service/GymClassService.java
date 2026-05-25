package com.academia.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.academia.dto.gymClass.CreateGymClassDTO;
import com.academia.dto.gymClass.GymClassResponseDTO;
import com.academia.dto.gymClass.UpdateGymClassDTO;
import com.academia.enums.GymClassStatus;
import com.academia.model.GymClassModel;
import com.academia.model.InstructorModel;
import com.academia.repository.GymClassRepository;
import com.academia.repository.InstructorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymClassService {

    private final GymClassRepository gymClassRepository;

    private final ReportService reportService;

    private final InstructorRepository instructorRepository;

    //CRUD BASIC OPERATIONS
    public GymClassResponseDTO createClass(CreateGymClassDTO dto) {
        GymClassModel gymClass = new GymClassModel();
        gymClass.setTypeClass(dto.getTypeClass());
        gymClass.setSchedule(dto.getSchedule());
        gymClass.setInstructorId(dto.getInstructorId());
        gymClass.setStudentsLimit(dto.getStudentsLimit());
        gymClass.setClassStatus(GymClassStatus.AVAILABLE);

        gymClassRepository.save(gymClass);

        return new GymClassResponseDTO(gymClass);
    }

    public GymClassResponseDTO updateClass(@NonNull String id, UpdateGymClassDTO dto) {
        GymClassModel gymClass = gymClassRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        gymClass.setTypeClass(gymClass.getTypeClass());
        gymClass.setStudentsLimit(gymClass.getStudentsLimit());
        gymClass.setInstructorId(gymClass.getInstructorId());
        gymClass.setClassStatus(gymClass.getClassStatus());

        gymClassRepository.save(gymClass);

        return new GymClassResponseDTO(gymClass);
    }

    public void deleteClass(@NonNull String id) {
        if (!gymClassRepository.existsById(id)) {
            throw new IllegalArgumentException("Class not found");
        }
        gymClassRepository.deleteById(id);
    }

    public GymClassResponseDTO getClassById(@NonNull String id) {
        GymClassModel gymClass = gymClassRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));
        
        return new GymClassResponseDTO(gymClass);
    }
    
    ///////////////////////////////REGRAS DE NEGÓCIO////////////////////////////////

    //Iniciar uma aula
    public void startClass(@NonNull String classId, String instructorId) {

        InstructorModel instructorModel = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        GymClassModel classModel = gymClassRepository.findById(classId)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new IllegalStateException("Instructor not authorized");
        }

        if (classModel.getClassStatus() != GymClassStatus.AVAILABLE) {
            throw new IllegalStateException("Class not available");
        }

        //Instrutor so iniciar e finalizar seu tipo de aula
        if (classModel.getTypeClass() != instructorModel.getSpecialty()) {
            throw new IllegalStateException("Instructor cannot control this type of class");
        }
        classModel.setClassStatus(GymClassStatus.INPROGRESS);
        gymClassRepository.save(classModel);
    }

    //Finalizar uma aula
    public void finishClass(@NonNull String classId, String instructorId) {

        InstructorModel instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        GymClassModel gymClass = gymClassRepository.findById(classId)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (gymClass.getClassStatus() != GymClassStatus.INPROGRESS) {
            throw new IllegalStateException("Class not in progress");
        }

        if (!gymClass.getInstructorId().equals(instructorId)) {
            throw new IllegalStateException("Instructor not authorized");
        }

        if (gymClass.getTypeClass() != instructor.getSpecialty()) {
            throw new IllegalStateException("Instructor cannot control this type of class");
        }

        gymClass.setClassStatus(GymClassStatus.FINISHED);
        gymClassRepository.save(gymClass);
        
        reportService.generateClassReport(gymClass);
    }
}
