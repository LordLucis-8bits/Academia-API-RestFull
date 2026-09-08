package com.academia.report;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.report.dto.ClassAttendanceReportDTO;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/reports")
@RequiredArgsConstructor 
public class ReportController {
    
    private final ReportService reportService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ClassAttendanceReportDTO> getAllReports() {
        return reportService.getAllReports();
    }
}
