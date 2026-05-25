package com.academia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.academia.dto.admin.CreateUserDTO;
import com.academia.dto.admin.UserResponseDTO;
import com.academia.dto.instructor.InstructorResponseDTO;
import com.academia.dto.student.StudentResponseDTO;
import com.academia.enums.PlanStatus;
import com.academia.enums.UserType;
import com.academia.model.InstructorModel;
import com.academia.model.StudentModel;
import com.academia.model.UserModel;
import com.academia.repository.InstructorRepository;
import com.academia.repository.StudentRepository;
import com.academia.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final InstructorRepository instructorRepository;

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    //Validação para garantir que os campos sejam preenchidos corretamente com base no tipo de usuário
    public void validate(CreateUserDTO dto) {
        if (dto.getRole() == UserType.STUDENT) {
            //o tipo de plano é obrigatório para estudantes
            if (dto.getPlanType() == null) { 
                throw new IllegalArgumentException("Plan type should not be provided for students at creation"); 
            }
            //o tipo de especialidade não deve ser fornecido para estudantes
            if (dto.getSpecialty() != null) { 
                throw new IllegalArgumentException("Specialty should not be provided for students"); 
            }

        } else if (dto.getRole() == UserType.INSTRUCTOR) {
            //o instrutor deve ter uma especialidade
            if (dto.getSpecialty() == null) {
                throw new IllegalArgumentException("Specialty is required for instructors at creation");
            }

            //o tipo de plano não deve ser fornecido para instrutores
            if (dto.getPlanType() != null) {
                throw new IllegalArgumentException("Plan type should not be provided for instructors at creation");
            }    
        }
    }

    //Criação de usuário feita pelo adiministrador
    public UserResponseDTO createUser(CreateUserDTO dto) {
        validate(dto);

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        UserModel user = new UserModel();
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        userRepository.save(user);

        try {
            //Cria perfil do usuario com base no role
            if (dto.getRole() == UserType.STUDENT) {
                createStudentProfile(user, dto);
            } else if (dto.getRole() == UserType.INSTRUCTOR) { 
                createInstructorProfile(user, dto);
            }
        } catch (Exception e) {
            //Se ocorrer um erro ao criar o perfil, exclui o usuario criado para evitar inconsistências
            userRepository.deleteById(user.getId());
            throw new RuntimeException("Failed to create user profile: " + e.getMessage());
        }

        return new UserResponseDTO();
    }

    //Metodos privados para criar perfil de estudante e instrutor
    private void createStudentProfile(UserModel user, CreateUserDTO dto) {
        StudentModel student = new StudentModel();
        student.setUserId(user.getId());
        student.setPlanType(dto.getPlanType());
        student.setPlanStart(LocalDate.now());
        student.setPlanEnd(studentService.calculateEndDate(dto.getPlanType(), LocalDate.now()));
        student.setPlanStatus(PlanStatus.ACTIVE);
        
        studentRepository.save(student);
    }
    
    private void createInstructorProfile(UserModel user, CreateUserDTO dto) {
    InstructorModel instructor = new InstructorModel(); 
    instructor.setUserId(user.getId());
    instructor.setSpecialty(dto.getSpecialty());
    
    instructorRepository.save(instructor);
    }

    //Listar alunos
    public List<StudentResponseDTO> listAllStudents() {
        return studentRepository.findAll().stream()
        .map(student -> {
            UserModel user = userRepository.findById(student.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("Students not found"));

            return new StudentResponseDTO(student, user);
        })
        .toList();
    }

    //Lista instrutores
    public List<InstructorResponseDTO> listAllInstructors() {
        return instructorRepository.findAll().stream()
        .map(instructor -> {
            UserModel user = userRepository.findById(instructor.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("Instructors not found"));
            
            return new InstructorResponseDTO(instructor, user);
        })
        .toList();
    }
}
