package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Enum.TypeClass;
import com.academia.Model.InstructorModel;

public interface InstructorRepository extends MongoRepository<InstructorModel, String> {
    
    Optional<InstructorModel> findByEmail(String email); 

    List<InstructorModel> findBySpecialization(TypeClass specialty);

    boolean existsByEmail(String email);
}