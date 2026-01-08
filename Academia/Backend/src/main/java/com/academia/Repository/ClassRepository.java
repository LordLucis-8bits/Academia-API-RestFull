package com.academia.Repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Enum.ClassStatus;
import com.academia.Enum.TypeClass;
import com.academia.Model.ClassModel;

public interface ClassRepository extends MongoRepository<ClassModel, String> {
    
    List<ClassModel> findByTypeClass(TypeClass typeClass);

    List<ClassModel> findByInstructorId(String instructorId);

    List<ClassModel> findByClassStatus(ClassStatus classStatus);
    
}
