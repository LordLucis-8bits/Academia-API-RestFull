package com.academia.gymClass;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.shared.enums.GymClassStatus;
import com.academia.shared.enums.TypeClass;

public interface GymClassRepository extends MongoRepository<GymClassModel, String> {
    
    List<GymClassModel> findByTypeClass(TypeClass typeClass);

    List<GymClassModel> findByInstructorId(String instructorId);

    List<GymClassModel> findByClassStatus(GymClassStatus classStatus);
    
}
