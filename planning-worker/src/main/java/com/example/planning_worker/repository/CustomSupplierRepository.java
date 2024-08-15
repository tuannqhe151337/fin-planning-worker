package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Supplier;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomSupplierRepository {
    List<Supplier> getSupplierWithPagination(String query, Pageable pageable);
}
