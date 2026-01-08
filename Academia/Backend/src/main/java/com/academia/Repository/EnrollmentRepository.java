package com.academia.Repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.EnrollmentModel;

public interface EnrollmentRepository extends MongoRepository<EnrollmentModel, String> {
    
    List<EnrollmentModel> findByStudentId(String studentsId);

    List<EnrollmentModel> findByInstructorId(String instructorId);

    List<EnrollmentModel> findByClassId(String classId);

    boolean existsByStudentIdAndClassId(String studentId, String classId);
}