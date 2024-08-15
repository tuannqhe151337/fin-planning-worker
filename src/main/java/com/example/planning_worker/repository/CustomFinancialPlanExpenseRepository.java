package com.example.planning_worker.repository;

import com.example.planning_worker.entity.FinancialPlanExpense;
import com.example.planning_worker.repository.result.ReportExpenseResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomFinancialPlanExpenseRepository {
    void saveListExpenses(List<FinancialPlanExpense> expenses);

    List<FinancialPlanExpense> getListExpenseWithPaginate(Long planId, String query, Long statusId, Long costTypeId, Long projectId, Long supplierId, Long picId, Pageable pageable);

    List<ReportExpenseResult> getListExpenseForReport(Long reportId, String query, Integer departmentId, Integer statusId, Integer costTypeId, Integer projectId, Integer supplierId, Integer picId, Pageable pageable);
}