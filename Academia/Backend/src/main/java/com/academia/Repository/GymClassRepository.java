package com.academia.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.enums.GymClassStatus;
import com.academia.enums.TypeClass;
import com.academia.model.GymClassModel;

public interface GymClassRepository extends MongoRepository<GymClassModel, String> {
    
    List<GymClassModel> findByTypeClass(TypeClass typeClass);

    List<GymClassModel> findByInstructorId(String instructorId);

    List<GymClassModel> findByClassStatus(GymClassStatus classStatus);
    
}
