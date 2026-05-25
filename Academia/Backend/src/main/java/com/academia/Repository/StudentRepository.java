package com.academia.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.model.StudentModel;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

    Optional<StudentModel> findByUserId(String id);
}
