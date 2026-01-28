package com.academia.Service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.Enum.ClassStatus;
import com.academia.Enum.PlanStatus;
import com.academia.Enum.PlanType;
import com.academia.Model.ClassModel;
import com.academia.Model.StudentModel;
import com.academia.Repository.ClassRepository;
import com.academia.Repository.StudentRepository;
import org.springframework.lang.NonNull;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
     
    @Autowired
    private ClassRepository classRepository;

    //CRUD BASIC OPERATIONS
    public StudentModel createStudent(StudentModel student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return studentRepository.save(student);
    }

    public StudentModel updateStudent(@NonNull String id, StudentModel updatedStudent) {
        StudentModel student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    
        student.setUsersTypes(updatedStudent.getUsersTypes());
        student.setPlanStatus(updatedStudent.getPlanStatus());
        student.setPlanType(updatedStudent.getPlanType());
        return studentRepository.save(student);
    }

    public void deleteStudent(@NonNull String id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    public StudentModel getStudentById(@NonNull String id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    //REGRAS DE NEGÓCIO
    //Alunos so ve aulas com status Disponivel
    public List<ClassModel> listAvailableClasses() {
        return classRepository.findByClassStatus(ClassStatus.AVAILABLE);
    } 

    public boolean checkPlanStatus(@NonNull String studentId) {
    StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return student.isStudentPlanActive();
    }

    //Renovar plano do aluno
    public void renewPlan(@NonNull String studentId, PlanType newPlanType, LocalDate newStartDate) {
        StudentModel student = studentRepository.findById(studentId).
        orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (newPlanType == null || newStartDate == null) {
            throw new IllegalArgumentException("Plan type and start date must be provided");
        }

        LocalDate newEndDate = calculateEndDate(newPlanType, newStartDate);

        student.setPlanType(newPlanType);
        student.setPlanStart(newStartDate);
        student.setPlanEnd(newEndDate);
        student.setPlanStatus(PlanStatus.ACTIVE);

        studentRepository.save(student);
    }

    //Calcular data de termino do plano
    public LocalDate calculateEndDate(PlanType planType, LocalDate startDate) {
        switch (planType) {
            case DAILY:
                return startDate.plusDays(1);
            case MONTHLY:
                return startDate.plusMonths(1);
            case QUARTERLY:
                return startDate.plusMonths(3);
            case SEMMIANNUAL:
                return startDate.plusMonths(6);
            case ANNUAL:
                return startDate.plusYears(1);
            default:
                throw new IllegalArgumentException("Invalid plan type");
        }
    }
}
