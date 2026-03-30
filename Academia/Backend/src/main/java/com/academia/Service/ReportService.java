package com.academia.service;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.model.GymClassModel;
import com.academia.model.ReportModel;
import com.academia.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    //Gera relatório automaticamente ao finalizar aula
    public void generateClassReport(GymClassModel classModel) {
        ReportModel report = new ReportModel();
        report.setClassId(classModel.getGymClassId());
        report.setTimeGeneration(LocalDateTime.now());

        reportRepository.save(report);
    }
}