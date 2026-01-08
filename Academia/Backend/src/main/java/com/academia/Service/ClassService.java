package com.academia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.academia.Enum.ClassStatus;
import com.academia.Model.ClassModel;
import com.academia.Repository.ClassRepository;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ReportService reportService;

    //Inicia aula
    public void startClass(@NonNull String instructorId, @NonNull String classId) {

        ClassModel classModel = classRepository.findById(classId)
        .orElseThrow(() -> new RuntimeException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new RuntimeException("Unauthorized: Instructor does not match");
        }

        if (classModel.getClassStatus() != ClassStatus.AVAILABLE) {
            throw new RuntimeException("Class not available");
        }

        classModel.setClassStatus(ClassStatus.INPROGRESS);
        classRepository.save(classModel);
    }
    
    //Finaliza aula
    public void finishClass(@NonNull String instructorId, @NonNull String classId) {
        ClassModel classModel = classRepository.findById(classId)
        .orElseThrow(() -> new RuntimeException("Class not found"));

        if (!classModel.getInstructorId().equals(instructorId)) {
            throw new RuntimeException("Unauthorized: Instructor does not match");
        }

        if (classModel.getClassStatus() != ClassStatus.INPROGRESS) {
            throw new RuntimeException("Class not in progress");
        }

        classModel.setClassStatus(ClassStatus.FINISHED);
        classRepository.save(classModel);

        //Gerar relatório ao finalizar a aula automaticamente
        reportService.generateClassReport(classModel);
    }
}