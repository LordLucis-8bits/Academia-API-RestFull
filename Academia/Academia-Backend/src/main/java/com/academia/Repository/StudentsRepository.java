package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.EnrollmentModel;
import com.academia.Model.StudentsModel;

public interface StudentsRepository extends MongoRepository<StudentsModel, String> {

    Optional<StudentsModel> findByName(String name);

    Optional<StudentsModel> findByUsername(String email);

    List<StudentsModel> findByEnrollment(EnrollmentModel enrollment);

    List<StudentsModel> findByAll();
}
