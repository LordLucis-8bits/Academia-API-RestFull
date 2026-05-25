package com.academia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.model.EnrollmentModel;

public interface EnrollmentRepository extends MongoRepository<EnrollmentModel, String> {
    
    List<EnrollmentModel> findByStudentId(String studentsId);

    List<EnrollmentModel> findByClassId(String id);

    boolean existsByStudentIdAndClassId(String studentId, String classId);

    long countByClassId(String classId);
}