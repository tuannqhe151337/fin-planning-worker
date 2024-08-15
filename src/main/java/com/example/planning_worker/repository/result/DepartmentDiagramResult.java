package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface DepartmentDiagramResult {
    Long getDepartmentId();
    String getDepartmentName();
    BigDecimal getTotalCost();
}
