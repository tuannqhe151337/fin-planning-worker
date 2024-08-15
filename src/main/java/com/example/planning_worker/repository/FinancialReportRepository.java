package com.example.planning_worker.repository;

import com.example.planning_worker.entity.FinancialReport;
import com.example.planning_worker.repository.result.FileNameResult;
import com.example.planning_worker.repository.result.ReportDetailResult;
import com.example.planning_worker.repository.result.YearDiagramResult;
import com.example.planning_worker.service.result.TotalCostByCurrencyResult;
import com.example.planning_worker.utils.enums.ExpenseStatusCode;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialReportRepository extends JpaRepository<FinancialReport, Long> {
    @Query(value = " SELECT DISTINCT count(report.id) FROM FinancialReport report " +
            " WHERE report.name like %:query% AND " +
            " (:termId IS NULL OR report.term.id = :termId) AND " +
            " (:statusId IS NULL OR report.status.id = :statusId) AND " +
            " report.isDelete = false ")
    long countDistinctListReportPaginate(@Param("query") String query, @Param("termId") Long termId, @Param("statusId") Long statusId);

    @Query(" SELECT report.id AS reportId, report.name AS name, term.id AS termId, term.name AS termName, status.id AS statusId, status.code AS statusCode, status.name AS statusName, " +
            " report.createdAt AS createdAt, term.startDate AS startDate, term.endDate AS endDate, term.allowReupload AS allowReupload, term.reuploadStartDate AS reuploadStartDate, term.reuploadEndDate AS reuploadEndDate, term.finalEndTermDate AS finalEndTermDate " +
            " FROM FinancialReport report " +
            " JOIN report.term term " +
            " JOIN report.status status " +
            " WHERE report.id = :reportId AND " +
            " report.isDelete = false OR report.isDelete is null ")
    ReportDetailResult getFinancialReportById(Long reportId);

    @Query(value = " SELECT term.name AS termName FROM FinancialReport report " +
            " JOIN report.term term " +
            " WHERE report.id = :reportId AND " +
            " report.isDelete = false AND term.isDelete = false ")
    FileNameResult generateFileName(Long reportId);

    @Query(" SELECT SUM(expense.amount*expense.unitPrice) FROM FinancialPlanExpense expense " +
            " LEFT JOIN expense.files files " +
            " LEFT JOIN files.file file " +
            " LEFT JOIN file.plan plan " +
            " LEFT JOIN plan.department department " +
            " LEFT JOIN expense.status status " +
            " LEFT JOIN expense.costType costType " +
            " WHERE file.id IN (SELECT MAX(file_2.id) FROM FinancialPlanFile file_2 " +
            "                       JOIN file_2.plan plan_2 " +
            "                       JOIN plan_2.term term_2 " +
            "                       JOIN term_2.financialReports report_2 " +
            "                       WHERE report_2.id = :reportId AND " +
            "                       (report_2.isDelete = false OR report_2.isDelete is null )" +
            "                       GROUP BY file_2.id) " +
            " AND " +
            " expense.status.code = :approved AND " +
            " (expense.isDelete = false OR expense.isDelete is null) ")
    BigDecimal calculateActualCostByReportId(Long reportId, ExpenseStatusCode approved);

    @Query(" SELECT SUM(expense.amount*expense.unitPrice) FROM FinancialPlanExpense expense " +
            " LEFT JOIN expense.files files " +
            " LEFT JOIN files.file file " +
            " LEFT JOIN file.plan plan " +
            " LEFT JOIN plan.department department " +
            " LEFT JOIN expense.status status " +
            " LEFT JOIN expense.costType costType " +
            " WHERE file.id IN (SELECT MAX(file_2.id) FROM FinancialPlanFile file_2 " +
            "                       JOIN file_2.plan plan_2 " +
            "                       JOIN plan_2.term term_2 " +
            "                       JOIN term_2.financialReports report_2 " +
            "                       WHERE report_2.id = :reportId AND " +
            "                       (report_2.isDelete = false OR report_2.isDelete is null)" +
            "                       GROUP BY file_2.id)" +
            " AND " +
            " (expense.isDelete = false OR expense.isDelete is null) ")
    BigDecimal calculateExpectedCostByReportId(Long reportId);

    @Query(" SELECT month (report.month) AS month, report.actualCost AS actualCost, report.expectedCost AS expectedCost FROM FinancialReport report " +
            " WHERE year(report.month) = :year ")
    List<YearDiagramResult> generateYearDiagram(Integer year);

    FinancialReport getReferenceByTermId(Long termId);

    @Query(" SELECT expense.planExpenseKey FROM FinancialPlanExpense expense " +
            " LEFT JOIN expense.files fileExpense " +
            " LEFT JOIN fileExpense.file file " +
            " WHERE file.id IN (SELECT MAX(file_2.id) FROM FinancialPlanFile file_2 " +
            "                       JOIN file_2.plan plan_2 " +
            "                       JOIN plan_2.term term_2 " +
            "                       JOIN term_2.financialReports report_2 " +
            "                       WHERE report_2.id = :reportId AND " +
            "                       (report_2.isDelete = false OR report_2.isDelete is null)" +
            "                       GROUP BY file_2.id)" +
            "ORDER BY length(expense.planExpenseKey) desc, expense.planExpenseKey desc LIMIT 1 ")
    String getLastCodeInReport(Long reportId);

    @Query(" SELECT expense.currency.id AS currencyId, month(expense.createdAt) AS month, year(expense.createdAt) AS year , SUM(expense.amount*expense.unitPrice) AS totalCost FROM FinancialPlanExpense expense " +
            " LEFT JOIN expense.files files " +
            " LEFT JOIN files.file file " +
            " LEFT JOIN file.plan plan " +
            " LEFT JOIN plan.department department " +
            " LEFT JOIN expense.status status " +
            " LEFT JOIN expense.costType costType " +
            " WHERE file.id IN (SELECT MAX(file_2.id) FROM FinancialPlanFile file_2 " +
            "                       JOIN file_2.plan plan_2 " +
            "                       JOIN plan_2.term term_2 " +
            "                       JOIN term_2.financialReports report_2 " +
            "                       WHERE report_2.id = :reportId AND " +
            "                       (report_2.isDelete = false OR report_2.isDelete is null )" +
            "                       GROUP BY file_2.id) " +
            " AND " +
            " (expense.status.code = :statusCode OR :statusCode is null) AND " +
            " (expense.isDelete = false OR expense.isDelete is null) " +
            " GROUP BY currencyId, month, year ")
    List<TotalCostByCurrencyResult> calculateCostByReportIdAndStatus(Long reportId, ExpenseStatusCode statusCode);


//    @Query(value = " SELECT expenses FROM FinancialReportExpense expenses " +
//            " JOIN expenses.financialReport report " +
//            " WHERE report.id = :reportId AND " +
//            " expenses.isDelete = false AND report.isDelete = false ")
//    List<ExpenseResult> getListExpenseByReportId(Long reportId);

//    @Query(value = " SELECT report.department.id FROM FinancialReport report " +
//            " WHERE report.id = :reportId AND " +
//            " report.isDelete = false ")
//    long getDepartmentId(Long reportId);
}