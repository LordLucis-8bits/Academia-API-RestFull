package com.academia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.academia.Enum.ClassStatus;
import com.academia.Model.ClassModel;
import com.academia.Model.EnrollmentModel;
import com.academia.Model.StudentsModel;
import com.academia.Repository.ClassRepository;
import com.academia.Repository.EnrollmentRepository;
import com.academia.Repository.StudentRepository;

@Service
public class EnrollmentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public void enrollStudent(@NonNull String studentId, @NonNull String classId) {
        StudentsModel studentsModel = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        ClassModel classModel = classRepository.findById(classId)
        .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        //Verifica se o plano do estudante está ativo
        if (!studentsModel.isStudentPlanActive()) {
            throw new IllegalStateException("Inactive plan");
        }

        //Verifica se a aula está cheia
        if (classModel.getEnrolledStudents().size() >= classModel.getStudentsLimit()) {
            throw new IllegalStateException("Class is full");
        }

        //Verifica se a aula esta com status disponível
        if (classModel.getClassStatus() != ClassStatus.AVAILABLE) {
            throw new IllegalStateException("Class is not available for enrollment");
        }

        //Verifica se o estudante já está matriculado na aula
        if (enrollmentRepository.existsByStudentIdAndClassId(studentId, classId)) {
            throw new IllegalStateException("Student already enrolled in this class");
        }

        //Instanciando o modelo de matrícula e salvando no repositório
        EnrollmentModel enrollmentModel = new EnrollmentModel(studentId, classId);
        enrollmentRepository.save(enrollmentModel);

        classModel.getEnrolledStudents().add(studentId);
        classRepository.save(classModel);
    }
}
          