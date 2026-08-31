package com.academia.report;

import java.util.List;
import org.springframework.stereotype.Service;

import com.academia.enrollment.EnrollmentModel;
import com.academia.enrollment.EnrollmentRepository;
import com.academia.gymClass.GymClassModel;
import com.academia.report.dto.StudentAttendanceDTO;
import com.academia.shared.UserModel;
import com.academia.shared.UserRepository;
import com.academia.student.StudentModel;
import com.academia.student.StudentRepository;

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

            StudentAttendanceDTO attendance = new StudentAttendanceDTO(users, student);
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