package com.example.planning_worker.repository;

import com.example.planning_worker.entity.AnnualReport;
import com.example.planning_worker.repository.result.AnnualReportExpenseResult;
import com.example.planning_worker.repository.result.AnnualReportResult;
import com.example.planning_worker.repository.result.CostTypeDiagramResult;
import com.example.planning_worker.repository.result.ReportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AnnualReportRepository extends JpaRepository<AnnualReport, Long> {
    @Query(value = "SELECT count( distinct(annualReport)) FROM AnnualReport annualReport " +
            " WHERE (annualReport.year = :year OR :year IS NULL) AND " +
            " annualReport.isDelete = false ")
    long countDistinctListAnnualReportPaging(String year);

    @Query(value = " SELECT year (term.finalEndTermDate) AS year, count ( distinct term.id) AS totalTerm, " +
            " count ( distinct department.id) AS totalDepartment FROM Term term " +
            " JOIN term.financialPlans plans " +
            " JOIN plans.department department " +
            " WHERE year (term.finalEndTermDate) = year (:now) AND " +
            " term.isDelete = false AND " +
            " department.isDelete = false " +
            " GROUP BY year ")
    AnnualReportResult getAnnualReport(LocalDate now);

    @Query(value = " SELECT report.department.id AS departmentId, sum(report.totalExpense) AS totalExpense, max(report.biggestExpenditure) AS biggestExpense, report.costType.id AS costTypeId FROM ReportStatistical report " +
            " WHERE year(report.createdAt) = year(:now) " +
            " GROUP BY departmentId, costTypeId ")
    List<ReportResult> generateReport(LocalDate now);

    @Query(value = " SELECT count (distinct (report.id)) FROM Report report " +
            " JOIN report.annualReport annualReport " +
            " JOIN report.costType costType " +
            " JOIN report.department department " +
            " WHERE annualReport.id = :annualReportId AND " +
            " (:departmentId IS NULL OR report.department.id = :departmentId) AND " +
            " (:costTypeId IS NULL OR report.costType.id = :costTypeId) AND " +
            " report.isDelete = false OR report.isDelete is null ")
    long countDistinctListExpenseWithPaginate(Long annualReportId, Long costTypeId, Long departmentId);

    @Query(value = " SELECT report.costType.id AS costTypeId, report.costType.name AS costTypeName, sum(report.totalExpense) AS totalCost FROM AnnualReport annualReport " +
            " JOIN annualReport.reports report " +
            " WHERE annualReport.id = :annualReportId AND " +
            " annualReport.isDelete = false AND report.isDelete = false " +
            " GROUP BY costTypeId, costTypeName ")
    List<CostTypeDiagramResult> getAnnualReportCostTypeDiagram(Long annualReportId);

    @Query(value = " SELECT report.department.name AS department, report.totalExpense AS totalExpense, report.biggestExpenditure AS biggestExpenditure, report.costType.name AS costType FROM Report report " +
            " WHERE report.annualReport.id = :annualReportId AND " +
            " report.isDelete = false ")
    List<AnnualReportExpenseResult> getListExpenseByAnnualReportId(Long annualReportId);

    @Query(value = " SELECT annualReport.year FROM AnnualReport annualReport " +
            " WHERE annualReport.id = :annualReportId AND " +
            " annualReport.isDelete = false ")
    String getYear(Long annualReportId);

    AnnualReport findByYear(Integer year);
}
