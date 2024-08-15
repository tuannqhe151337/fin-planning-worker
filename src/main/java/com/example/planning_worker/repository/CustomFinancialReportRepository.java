package com.example.planning_worker.repository;

import com.example.planning_worker.entity.FinancialReport;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomFinancialReportRepository {
    List<FinancialReport> getReportWithPagination(String query, Long termId, Long statusId, Pageable pageable);

}