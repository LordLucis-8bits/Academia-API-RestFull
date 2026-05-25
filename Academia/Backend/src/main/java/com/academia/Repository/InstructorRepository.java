package com.academia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.enums.TypeClass;
import com.academia.model.InstructorModel;

public interface InstructorRepository extends MongoRepository<InstructorModel, String> {
    
    List<InstructorModel> findBySpecialty(TypeClass specialty);
    
}