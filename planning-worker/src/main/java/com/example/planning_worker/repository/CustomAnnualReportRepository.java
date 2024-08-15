package com.example.planning_worker.repository;

import com.example.planning_worker.entity.AnnualReport;
import com.example.planning_worker.entity.Report;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomAnnualReportRepository {
    List<AnnualReport> getListAnnualReportPaging(Pageable pageable, String year);

    List<Report> getListExpenseWithPaginate(Long annualReportId, Long costTypeId, Long departmentId, Pageable pageable);
}
