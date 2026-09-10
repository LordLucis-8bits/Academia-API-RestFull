package com.academia.gymClass;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.shared.enums.GymClassStatus;
import com.academia.shared.enums.TypeClass;

public interface GymClassRepository extends MongoRepository<GymClassModel, String> {
    
    List<GymClassModel> findByTypeClass(TypeClass typeClass);

    Optional<GymClassModel> findByInstructorId(String classId);

    List<GymClassModel> findByUserId(String userId);

    List<GymClassModel> findByClassStatus(GymClassStatus classStatus);
    
}
