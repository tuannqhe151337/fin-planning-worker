package com.example.planning_worker.repository.result;

import com.example.planning_worker.utils.enums.ExpenseStatusCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExpenseResult {
    String getExpenseCode();

    String getExpenseName();

    LocalDateTime getDate();

    String getTermName();

    String getDepartmentName();

    String getCostTypeName();

    BigDecimal getUnitPrice();

    Integer getAmount();

    BigDecimal getTotal();

    String getProjectName();

    String getSupplierName();

    String getPicName();

    String getNote();

    Long getExpenseId();

    ExpenseStatusCode getStatusCode();

    Long getDepartmentId();

    Long getCostTypeId();

    Long getStatusId();

    String getCurrencyName();
}