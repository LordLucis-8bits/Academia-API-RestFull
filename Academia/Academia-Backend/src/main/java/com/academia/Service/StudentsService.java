package com.academia.Service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.Enum.ClassStatus;
import com.academia.Enum.PlanStatus;
import com.academia.Enum.PlanType;
import com.academia.Model.ClassModel;
import com.academia.Model.StudentsModel;
import com.academia.Repository.ClassRepository;
import com.academia.Repository.StudentsRepository;

@Service
public class StudentsService {
    
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ClassRepository classRepository;

    public boolean classRegistration(ClassModel classModel, StudentsModel studentsModel) {
        //Verifica valor nulo
        if (classModel == null || studentsModel == null) {
            System.err.println("Class or Student information is missing.");
            return false;
        }
        //Verifica se a aula está disponivel
        if (!classModel.getClassStatus().equals(ClassStatus.AVAILABLE)) {
            System.err.println("Class is not available for registration.");
            return false;
        }
        //Verifica se já está matriculado
        boolean alreadyEnrolled = studentsModel.getEnrolledClasses().stream()
        .anyMatch(enrolledClass -> enrolledClass.getId().equals(classModel.getId()));
        if (alreadyEnrolled) {
            System.err.println("Student is already enrolled in this class.");
            return false;
        }
        studentsModel.getEnrolledClasses().add(classModel);
        classModel.getEnrolledStudents().add(studentsModel);

        classRepository.save(classModel);
        studentsRepository.save(studentsModel);

        System.out.println("Students successfully enrolled in class!");
        return true;
    }

    protected LocalDate calculateDateEndPlan(PlanType planType, LocalDate dateStartPlan) {
        if (planType == null || dateStartPlan == null) {
            throw new IllegalArgumentException("planType and dateStartPlan is value mandatory!");
        }
        switch (planType) {
            case DAILY:
            return dateStartPlan.plusDays(1);

            case MONTHLY:
            return dateStartPlan.plusMonths(1);

            case QUARTERLY:
            return dateStartPlan.plusMonths(3);

            case SEMMIANNUAL:
            return dateStartPlan.plusMonths(6);

            case ANNUAL:
            return dateStartPlan.plusYears(1);

            default:
            throw new IllegalArgumentException("This type of plan is invalid!" + planType);
        }
    }

    public PlanStatus checkPlanStatus(LocalDate dateStart, LocalDate dateEnd) {
        LocalDate today = LocalDate.now();
        if (today.isBefore(dateStart) || today.isAfter(dateEnd)) {
            return PlanStatus.INACTIVE;
        }
        return PlanStatus.ACTIVE;
    }
}

