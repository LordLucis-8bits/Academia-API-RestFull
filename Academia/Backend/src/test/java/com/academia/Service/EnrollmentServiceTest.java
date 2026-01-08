package com.academia.Service;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.academia.Model.*;
import com.academia.Repository.*;
import com.academia.Enum.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClassRepository classRepository;

    @Mock
    EnrollmentRepository enrollmentRepository;
    @SuppressWarnings("null")
    
    @Test
    void shouldEnrollStudentInClassSuccessfully() {
        StudentsModel student = new StudentsModel();
        student.setId("student123");
        student.setPlanStatus(PlanStatus.ACTIVE);

        ClassModel classModel = new ClassModel();
        classModel.setId("class123");
        classModel.setClassStatus(ClassStatus.AVAILABLE);
        classModel.setStudentsLimit(10);
        classModel.setEnrolledStudents(new ArrayList<>());

        when(studentRepository.findById("student123"))
        .thenReturn(Optional.of(student));
        
        when(classRepository.findById("class123"))
        .thenReturn(Optional.of(classModel));

        enrollmentService.enrollStudent("student123", "class123");

        verify(enrollmentRepository, times(1)).save(any(EnrollmentModel.class));

    }
}