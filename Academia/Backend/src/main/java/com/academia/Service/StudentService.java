package com.academia.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.academia.dto.admin.UpdateUserDTO;
import com.academia.dto.gymClass.GymClassResponseDTO;
import com.academia.dto.student.StudentResponseDTO;
import com.academia.dto.student.UpdateStudentDTO;
import com.academia.enums.GymClassStatus;
import com.academia.enums.PlanStatus;
import com.academia.enums.PlanType;
import com.academia.model.EnrollmentModel;
import com.academia.model.GymClassModel;
import com.academia.model.StudentModel;
import com.academia.model.UserModel;
import com.academia.repository.EnrollmentRepository;
import com.academia.repository.GymClassRepository;
import com.academia.repository.StudentRepository;
import com.academia.repository.UserRepository;
import com.academia.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    
    private final StudentRepository studentRepository;
     
    private final GymClassRepository gymClassRepository;

    private final EnrollmentRepository enrollmentRepository;

    public StudentResponseDTO updateStudent(@NonNull String userId, @NonNull String studentId, 
        UpdateUserDTO userDTO, UpdateStudentDTO studentDTO) {

        UserModel user = userRepository.findById(userId)  
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        StudentModel student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (userDTO != null) {
            if (userDTO.getName() != null) {
                user.setName(userDTO.getName());
            }

            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }

            if (userDTO.getAge() != null) {
                user.setAge(userDTO.getAge());
            }
        }

        userRepository.save(user);

        if (studentDTO != null) {
            if (studentDTO.getPlanType() != null) {
                student.setPlanType(studentDTO.getPlanType());
            }

            if (studentDTO.getPlanStatus() != null) {
                student.setPlanStatus(studentDTO.getPlanStatus());
            }
        } 
        
        studentRepository.save(student);

        return new StudentResponseDTO(student, user);
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    //Admin obtem informações do aluno
    public StudentResponseDTO getStudentById(String id) {
        StudentModel student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        UserModel user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new StudentResponseDTO(student, user);
    }

    //O aluno logado pode ver suas próprias informações
    public StudentResponseDTO getCurrentStudentInfo() {
        UserModel user = SecurityUtils.getCurrentUser();

        StudentModel student = studentRepository.findByUserId(user.getId())
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return new StudentResponseDTO(student, user);
    }
    
    ////////////////////////////////////REGRAS DE NEGÓCIO//////////////////////////////////////

    //Aluno so ve aulas com status Disponivel
    public List<GymClassResponseDTO> listAvailableClasses() {
        List<GymClassModel> availableClasses = gymClassRepository.findByClassStatus(GymClassStatus.AVAILABLE);
        return availableClasses.stream()
        .map(GymClassResponseDTO::new)
        .toList();
    } 

    //Lista de aulas matriculada do aluno
    public List<EnrollmentModel> getStudentEnrollments(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    //Checar se status do plano 
    public boolean checkPlanStatus(String id) {
    StudentModel student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return student.isStudentPlanActive();
    }

    //Renovar plano do aluno
    public void renewPlan(PlanType newPlanType) {
     UserModel user = SecurityUtils.getCurrentUser();
     
     StudentModel student = studentRepository.findByUserId(user.getId())
     .orElseThrow(() -> new IllegalArgumentException("Student not found"));

     LocalDate newStartDate = LocalDate.now();
     
     LocalDate newEndDate = calculateEndDate(newPlanType, newStartDate);

     student.setPlanType(newPlanType);
     student.setPlanStart(newStartDate);
     student.setPlanEnd(newEndDate);
     student.setPlanStatus(PlanStatus.ACTIVE);

     studentRepository.save(student);
    
    }

    //Calcular data de termino do plano
    public LocalDate calculateEndDate(PlanType planType, LocalDate newStartDate) {
        switch (planType) {
            case DAILY:
                return newStartDate.plusDays(1);
            case MONTHLY:
                return newStartDate.plusMonths(1);
            case QUARTERLY:
                return newStartDate.plusMonths(3);
            case SEMMIANNUAL:
                return newStartDate.plusMonths(6);
            case ANNUAL:
                return newStartDate.plusYears(1);
            default:
                throw new IllegalArgumentException("Invalid plan type");
        }
    }
}
