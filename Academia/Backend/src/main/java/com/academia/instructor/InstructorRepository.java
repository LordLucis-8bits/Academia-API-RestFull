package com.academia.instructor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.shared.enums.TypeClass;

public interface InstructorRepository extends MongoRepository<InstructorModel, String> {

    Optional<InstructorModel> findByUserId(String userId);
    
    List<InstructorModel> findBySpecialty(TypeClass specialty);
    
}