package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.StudentModel;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

    Optional<StudentModel> findByEmail(String email);

    List<StudentModel> findByAge(int age);

    List<StudentModel> findByStudentId(String studentId);

    boolean existsByEmail(String email);
}
