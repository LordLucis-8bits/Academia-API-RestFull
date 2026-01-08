package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.StudentsModel;

public interface StudentRepository extends MongoRepository<StudentsModel, String> {

    Optional<StudentsModel> findByEmail(String email);

    List<StudentsModel> findByAge(int age);

    List<StudentsModel> findByStudentId(String studentId);

    boolean existsByEmail(String email);
}
