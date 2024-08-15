package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface ReportResult {
    Long getDepartmentId();
    BigDecimal getTotalExpense();
    BigDecimal getBiggestExpense();
    Long getCostTypeId();
}