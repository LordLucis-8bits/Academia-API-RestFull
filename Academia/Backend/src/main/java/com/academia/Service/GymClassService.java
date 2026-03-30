package com.academia.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.enums.GymClassStatus;
import com.academia.model.GymClassModel;
import com.academia.model.InstructorModel;
import com.academia.repository.GymClassRepository;
import org.springframework.lang.NonNull;

@Service
public class GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private InstructorModel instructorModel;


    //CRUD BASIC OPERATIONS
    public GymClassModel createClass(@NonNull GymClassModel classModel) {
        return gymClassRepository.save(classModel);
    }

    public GymClassModel updateClass(@NonNull String id, GymClassModel updateClass) {
        GymClassModel classModel = gymClassRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        classModel.setTypeClass(classModel.getTypeClass());
        classModel.setSchedule(classModel.getSchedule());
        classModel.setStudentsLimit(classModel.getStudentsLimit());
        classModel.setInstructorId(classModel.getInstructorId());
        classModel.setClassStatus(classModel.getClassStatus());
        classModel.setEnrolledStudents(classModel.getEnrolledStudents());
        return gymClassRepository.save(classModel);
    }

    public void deleteClass(@NonNull String id) {
        if (!gymClassRepository.existsById(id)) {
            throw new IllegalArgumentException("Class not found");
        }
        gymClassRepository.deleteById(id);
    }

    public GymClassModel getClassById(@NonNull String id) {
        return gymClassRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));
    }
    
    //REGRAS DE NEGÓCIO
    //Iniciar uma aula
    public void startClass(@NonNull String classId, String instructorId) {

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

        GymClassModel classModel = gymClassRepository.findById(classId)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new IllegalStateException("Instructor not authorized");
        }

        if (classModel.getClassStatus() != GymClassStatus.INPROGRESS) {
            throw new IllegalStateException("Class not in progress");
        }

        if (classModel.getTypeClass() != instructorModel.getSpecialty()) {
            throw new IllegalStateException("Instructor cannot control this type of class");
        }
        classModel.setClassStatus(GymClassStatus.FINISHED);
        gymClassRepository.save(classModel);

        reportService.generateClassReport(classModel);
    }
}
