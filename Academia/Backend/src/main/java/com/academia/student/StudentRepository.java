package com.academia.student;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

    Optional<StudentModel> findByUserId(String id);
}
