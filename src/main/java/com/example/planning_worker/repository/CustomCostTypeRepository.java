package com.example.planning_worker.repository;

import com.example.planning_worker.entity.CostType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCostTypeRepository {

    List<CostType> getListCostTypePaginate(String query, Pageable pageable);
}
