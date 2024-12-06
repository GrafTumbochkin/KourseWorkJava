package com.example.kourse.Controllers;

import com.example.kourse.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/summary")
    public String generateSummaryReport() {
        return reportService.generateSummaryReport();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue")
    public String generateRevenueReport() {
        return reportService.generateRevenueReport();
    }
}
