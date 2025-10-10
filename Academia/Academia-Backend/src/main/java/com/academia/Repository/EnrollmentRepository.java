package com.academia.Repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.ClassModel;
import com.academia.Model.EnrollmentModel;
import com.academia.Model.InstructorModel;
import com.academia.Model.StudentsModel;

public interface EnrollmentRepository extends MongoRepository<EnrollmentModel, String> {
    
    List<EnrollmentModel> findByStudentModel(StudentsModel studentsModel);

    List<EnrollmentModel> findByInstructorModel(InstructorModel instructorModel);

    List<EnrollmentModel> findByClassModel(ClassModel classModel);
}