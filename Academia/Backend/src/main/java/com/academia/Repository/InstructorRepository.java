package com.academia.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.enums.TypeClass;
import com.academia.model.InstructorModel;

public interface InstructorRepository extends MongoRepository<InstructorModel, String> {
    
    Optional<InstructorModel> findByEmail(String email); 

    List<InstructorModel> findBySpecialization(TypeClass specialty);

    boolean existsByEmail(String email);
}