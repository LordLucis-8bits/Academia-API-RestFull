package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.InstructorModel;

public interface InstructorRepository extends MongoRepository<InstructorModel, String> {
    
    Optional<InstructorModel> findByName(String name);

    Optional<InstructorModel> findBySpecialty(String specialty);

    List<InstructorModel> findByInstructor(InstructorModel instructorModel);

    List<InstructorModel> findByAll();
}
