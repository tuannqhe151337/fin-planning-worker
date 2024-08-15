package com.example.planning_worker.repository;

import com.example.planning_worker.entity.CostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CostTypeRepository extends JpaRepository<CostType, Long> {
    @Query(" SELECT count(distinct (costType.id)) FROM CostType costType " +
            " WHERE costType.name like %:query% AND costType.isDelete = false ")
    long countDistinctListCostType(String query);
}
