package com.example.planning_worker.service;

import com.example.planning_worker.entity.AnnualReport;
import com.example.planning_worker.entity.Report;
import com.example.planning_worker.repository.*;
import com.example.planning_worker.repository.result.AnnualReportResult;
import com.example.planning_worker.repository.result.ReportResult;
import com.example.planning_worker.utils.mapper.annual.AnnualReportMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnualReportScheduleService {
    private final AnnualReportRepository annualReportRepository;
    private final ReportRepository reportRepository;

    // Chạy vào ngày 20 tháng 12 hàng năm
    @Scheduled(cron = "0 0 0 20 12 ?")
    @Transactional
    public void generateAnnualReport() {

        // Generate annual report
        AnnualReportResult annualReportResult = annualReportRepository.getAnnualReport(LocalDate.now());
        if (annualReportResult != null) {

            AnnualReport annualReport = new AnnualReportMapperImpl().mapToAnnualReportMapping(annualReportResult);

            // Generate report for annual report
            List<ReportResult> reportResults = annualReportRepository.generateReport(LocalDate.now());
            if (reportResults != null) {

                long totalCostOfYear = 0L;

                for (ReportResult reportResult : reportResults) {
                    totalCostOfYear += reportResult.getTotalExpense().longValue();
                }

                List<Report> reports = new ArrayList<>();
                reportResults.forEach(reportResult -> {
                    Report report = new AnnualReportMapperImpl().mapToReportMapping(reportResult);
                    report.setAnnualReport(annualReport);
                    reports.add(report);
                });

                annualReport.setReports(reports);
                annualReport.setTotalExpense(BigDecimal.valueOf(totalCostOfYear));

                annualReportRepository.save(annualReport);
                reportRepository.saveAll(reports);
            }
        }
    }
}
