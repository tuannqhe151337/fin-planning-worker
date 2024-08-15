package com.example.planning_worker.repository;

import com.example.planning_worker.entity.FinancialPlan;
import com.example.planning_worker.repository.result.VersionResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomFinancialPlanRepository {

    List<VersionResult> getListVersionWithPaginate(Long planId, Pageable pageable);

    List<FinancialPlan> getPlanWithPagination(String query, Long termId, Long departmentId, Pageable pageable);
}