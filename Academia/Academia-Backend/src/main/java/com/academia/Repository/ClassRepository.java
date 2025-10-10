package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Enum.TypeClass;
import com.academia.Model.ClassModel;
import com.academia.Model.InstructorModel;

public interface ClassRepository extends MongoRepository<ClassModel, String> {
    
    Optional<ClassModel> findByTypeClass(TypeClass typeClass);

    Optional<ClassModel> findByInstructorModel(InstructorModel instructormModel);

    List<ClassModel> findByClassModel(ClassModel classModel);
}
