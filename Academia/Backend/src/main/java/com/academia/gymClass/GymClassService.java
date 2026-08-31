package com.academia.gymClass;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.academia.gymClass.dto.CreateGymClassDTO;
import com.academia.gymClass.dto.GymClassResponseDTO;
import com.academia.gymClass.dto.UpdateGymClassDTO;
import com.academia.instructor.InstructorModel;
import com.academia.instructor.InstructorRepository;
import com.academia.report.ReportService;
import com.academia.shared.enums.GymClassStatus;

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

        if (dto.getTypeClass() != null) {
            gymClass.setTypeClass(dto.getTypeClass());
        }

        if (dto.getSchedule() != null) {
            gymClass.setSchedule(dto.getSchedule());
        }
        
        if (dto.getInstructorId() != null) {
            gymClass.setInstructorId(dto.getInstructorId());
        }

        if (dto.getStudentsLimit() != null) {
            gymClass.setStudentsLimit(dto.getStudentsLimit());
        }
    
        if (dto.getClassStatus() != null) {
            gymClass.setClassStatus(dto.getClassStatus());
        }
        
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
    public void finishClass(@NonNull String classId, @NonNull String instructorId) {

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
