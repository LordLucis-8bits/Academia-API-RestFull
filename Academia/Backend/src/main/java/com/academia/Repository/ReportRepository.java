package com.academia.Repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.ReportModel;

public interface ReportRepository extends MongoRepository<ReportModel, String> {

    List<ReportModel> findByInstructorId(String instructorId);

    List<ReportModel> findByClassId(String classId);

    boolean existsByClassId(String classId);
}
