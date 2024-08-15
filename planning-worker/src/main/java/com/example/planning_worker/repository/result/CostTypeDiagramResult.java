package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface CostTypeDiagramResult {
    String getMonth();
    Long getCostTypeId();
    String getCostTypeName();
    BigDecimal getTotalCost();
}
