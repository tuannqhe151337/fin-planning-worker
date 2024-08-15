package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Report;
import com.example.planning_worker.repository.result.CostTypeDiagramResult;
import com.example.planning_worker.repository.result.DepartmentDiagramResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = " SELECT report.costType.id AS costTypeId, report.costType.name AS costTypeName, sum(report.totalExpense) AS totalCost FROM AnnualReport annualReport " +
            " JOIN annualReport.reports report " +
            " WHERE annualReport.year = :year AND " +
            " (report.department.id = :departmentId OR :departmentId is null) AND " +
            " annualReport.isDelete = false AND report.isDelete = false " +
            " GROUP BY costTypeId, costTypeName " +
            " ORDER BY totalCost desc")
    List<CostTypeDiagramResult> getCostTypeYearDiagram(Integer year, Long departmentId);

    @Query(value = " SELECT report.department.id AS departmentId, report.department.name AS departmentName, sum(report.totalExpense) AS totalCost FROM AnnualReport annualReport " +
            " JOIN annualReport.reports report " +
            " WHERE annualReport.year = :year AND " +
            " annualReport.isDelete = false AND report.isDelete = false " +
            " GROUP BY departmentId, departmentName " +
            " ORDER BY totalCost desc LIMIT 5")
    List<DepartmentDiagramResult> getDepartmentYearDiagram(Integer year);

    @Query(value = " SELECT concat(month (term.finalEndTermDate), '/', year(term.finalEndTermDate)) AS month , report.costType.id AS costTypeId, report.costType.name AS costTypeName, sum(report.totalExpense) AS totalCost FROM ReportStatistical report " +
            " JOIN report.report financialReport " +
            " JOIN financialReport.term term " +
            " WHERE year(term.finalEndTermDate) = :year AND " +
            " (report.department.id = :departmentId OR :departmentId is null) AND " +
            " report.isDelete = false " +
            " GROUP BY costTypeId, costTypeName, concat(month (term.finalEndTermDate), '/', year(term.finalEndTermDate)) ")
    List<CostTypeDiagramResult> getReportCostTypeDiagram(Integer year, Long departmentId);

}
