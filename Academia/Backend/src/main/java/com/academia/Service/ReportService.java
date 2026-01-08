package com.academia.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.Model.ClassModel;
import com.academia.Model.ReportModel;
import com.academia.Repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    //Gera relatório automaticamente ao finalizar aula
    public void generateClassReport(ClassModel classModel) {

        List<String> studentsIds = classModel.getEnrolledStudents();

        ReportModel report = new ReportModel();
        report.setClassId(classModel.getId());
        report.setTypeClass(classModel.getTypeClass());
        report.setInstructorId(classModel.getInstructorId());
        report.setStudentsId(studentsIds);
        report.setTimeGeneration(LocalDateTime.now());

        reportRepository.save(report);
    }
}