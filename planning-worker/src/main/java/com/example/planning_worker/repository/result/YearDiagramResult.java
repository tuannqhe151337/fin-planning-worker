package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface YearDiagramResult {
    Integer getMonth();

    BigDecimal getActualCost();

    BigDecimal getExpectedCost();
}
