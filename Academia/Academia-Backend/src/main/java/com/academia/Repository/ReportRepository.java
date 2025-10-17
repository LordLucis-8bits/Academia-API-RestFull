package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.ClassModel;
import com.academia.Model.InstructorModel;
import com.academia.Model.ReportModel;
import com.academia.Model.StudentsModel;

public interface ReportRepository extends MongoRepository<ReportModel, String> {

    Optional<ReportModel> findByStudentModel(StudentsModel studentsModel);

    List<ReportModel> findByInstructorModel(InstructorModel instructor);

    List<ReportModel> findByClassModel(ClassModel classModel);

    List<ReportModel> findByAll();
}
