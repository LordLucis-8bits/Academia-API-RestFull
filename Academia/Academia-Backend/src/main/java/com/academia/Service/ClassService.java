package com.academia.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.Model.ClassModel;
import com.academia.Model.ReportModel;
import com.academia.Model.StudentsModel;
import com.academia.Repository.ClassRepository;
import com.academia.Repository.InstructorRepository;
import com.academia.Repository.StudentsRepository;

@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private StudentsRepository studentsRepository; 
    
    //Metodos de controle de aulas gerenciada pelo instrutor
    //Inicia a aula
    public void classStart(ClassModel classModel) {

        //valores nulos
        if(classModel.getInstructor() == null || classModel.getId() == null || classModel.getClassStatus() == null) {
            throw new IllegalArgumentException("Instructor and Id cannot be null");
        }
        //verifica se o instrutor existe
        if(!instructorRepository.existsById(classModel.getInstructor().getId())) {
            throw new IllegalArgumentException("Instructor does not exist");
        }
        //verifica status da aula se é disponivel
        if (classModel.getClassStatus() != com.academia.Enum.ClassStatus.AVAILABLE) {
            throw new IllegalArgumentException("Class is not available to start");
        }
        classModel.setClassStatus(com.academia.Enum.ClassStatus.INPROGRESS);
        classRepository.save(classModel);

        System.out.println("Class started successfully!");
    }

    //Encerra a aula
    public ReportModel classEnd(String classId) {
        ClassModel classModel = classRepository.findById(classId)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));
        
        //verifica status da aula se está em andamento
        if (classModel.getClassStatus() == com.academia.Enum.ClassStatus.INPROGRESS) {
            throw new IllegalArgumentException("Class is not in progress to end");
        }
        classModel.setClassStatus(com.academia.Enum.ClassStatus.FINISHED);
        classRepository.save(classModel);

        //Buscar todos os alunos matriculados na aula
        List<StudentsModel> studentsParticipated = studentsRepository.findByAll()
        .stream()
        .filter(student -> student.getEnrolledClasses().stream()
        .anyMatch(enrolledClass -> enrolledClass.getId().equals(classId)))
        .collect(Collectors.toList());

        //Gerar relatorio
        ReportModel report = new ReportModel();
        report.setTypeClass(classModel.getTypeClass());
        report.setInstructor(classModel.getInstructor());
        report.setStudentsParticipated(studentsParticipated);
        report.setTimeGeneration(java.time.LocalDateTime.now());

        System.out.println("Class ended successfully!");
        return report;
    }
}