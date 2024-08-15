package com.example.planning_worker.repository.result;


public interface PlanVersionResult {
    Long getPlanId();
    Integer getVersion();
    String getTermName();
    String getDepartmentName();
}
