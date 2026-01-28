package com.academia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.Enum.ClassStatus;
import com.academia.Model.ClassModel;
import com.academia.Model.InstructorModel;
import com.academia.Repository.ClassRepository;
import org.springframework.lang.NonNull;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private InstructorModel instructorModel;


    //CRUD BASIC OPERATIONS
    public ClassModel createClass(@NonNull ClassModel classModel) {
        return classRepository.save(classModel);
    }

    public ClassModel updateClass(@NonNull String id, ClassModel updateClass) {
        ClassModel classModel = classRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        classModel.setTypeClass(classModel.getTypeClass());
        classModel.setSchedule(classModel.getSchedule());
        classModel.setStudentsLimit(classModel.getStudentsLimit());
        classModel.setInstructorId(classModel.getInstructorId());
        classModel.setClassStatus(classModel.getClassStatus());
        classModel.setEnrolledStudents(classModel.getEnrolledStudents());
        return classRepository.save(classModel);
    }

    public void deleteClass(@NonNull String id) {
        if (!classRepository.existsById(id)) {
            throw new IllegalArgumentException("Class not found");
        }
        classRepository.deleteById(id);
    }

    public ClassModel getClassById(@NonNull String id) {
        return classRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));
    }
    
    //REGRAS DE NEGÓCIO
    //Iniciar uma aula
    public void startClass(@NonNull String classId, String instructorId) {

        ClassModel classModel = classRepository.findById(classId)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new IllegalStateException("Instructor not authorized");
        }

        if (classModel.getClassStatus() != ClassStatus.AVAILABLE) {
            throw new IllegalStateException("Class not available");
        }

        //Instrutor so iniciar e finalizar seu tipo de aula
        if (classModel.getTypeClass() != instructorModel.getSpecialty()) {
            throw new IllegalStateException("Instructor cannot control this type of class");
        }
        classModel.setClassStatus(ClassStatus.INPROGRESS);
        classRepository.save(classModel);
    }

    //Finalizar uma aula
    public void finishClass(@NonNull String classId, String instructorId) {

        ClassModel classModel = classRepository.findById(classId)
            .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new IllegalStateException("Instructor not authorized");
        }

        if (classModel.getClassStatus() != ClassStatus.INPROGRESS) {
            throw new IllegalStateException("Class not in progress");
        }

        if (classModel.getTypeClass() != instructorModel.getSpecialty()) {
            throw new IllegalStateException("Instructor cannot control this type of class");
        }
        classModel.setClassStatus(ClassStatus.FINISHED);
        classRepository.save(classModel);

        reportService.generateClassReport(classModel);
    }
}
