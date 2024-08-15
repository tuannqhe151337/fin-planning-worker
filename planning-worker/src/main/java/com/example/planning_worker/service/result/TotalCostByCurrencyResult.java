package com.example.planning_worker.service.result;

import java.math.BigDecimal;


public interface TotalCostByCurrencyResult {
    Long getCurrencyId();

    Integer getMonth();

    Integer getYear();

    BigDecimal getTotalCost();
}
