package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface CostStatisticalByCurrencyResult {
    Long getCurrencyId();

    Integer getMonth();

    Integer getYear();

    BigDecimal getCost();

    BigDecimal getBiggestCost();

    Integer getDepartmentId();

    Integer getCostTypeId();
}
