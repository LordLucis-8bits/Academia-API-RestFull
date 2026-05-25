package com.academia.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.academia.dto.report.StudentAttendanceDTO;
import com.academia.model.EnrollmentModel;
import com.academia.model.GymClassModel;
import com.academia.model.ReportModel;
import com.academia.model.StudentModel;
import com.academia.model.UserModel;
import com.academia.repository.EnrollmentRepository;
import com.academia.repository.ReportRepository;
import com.academia.repository.StudentRepository;
import com.academia.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;
    
    private final ReportRepository reportRepository;

    private final EnrollmentRepository enrollmentRepository;

    //Gera relatório automaticamente ao finalizar aula
    public void generateClassReport(GymClassModel gymClass) {
        //Pega o id da aula e busca matriculas associadas a ela
        List<EnrollmentModel> enrollments = enrollmentRepository.findByClassId(gymClass.getId());

        //Mapeia as matriculas para criar uma lista de StudentAttendanceDTO
        List<StudentAttendanceDTO> studentAttendance = enrollments.stream()
        .map(enroll -> { 

            StudentModel student = studentRepository.findById(enroll.getStudentId())
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));

            UserModel users = userRepository.findById(student.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

            StudentAttendanceDTO attendance = new StudentAttendanceDTO();
            attendance.setId(student.getId());
            attendance.setName(users.getName());
            attendance.setPresent(true);

            return attendance;
        }).toList();

        //Cria um novo relatório e preenche os dados
        ReportModel report = new ReportModel();
        report.setClassId(gymClass.getId());
        report.setNameClass(gymClass.getTypeClass());
        report.setInstructorId(gymClass.getInstructorId());
        report.setTotalStudents(enrollments.size());
        report.setStudentAttendances(studentAttendance);

        reportRepository.save(report);
    }
}