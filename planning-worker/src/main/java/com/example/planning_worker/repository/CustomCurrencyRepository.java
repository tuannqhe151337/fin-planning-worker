package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Currency;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCurrencyRepository {
    List<Currency> getCurrencyWithPagination(String query, Pageable pageable);
}
