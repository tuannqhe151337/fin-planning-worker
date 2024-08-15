package com.example.planning_worker.repository;

import com.example.planning_worker.entity.CurrencyExchangeRate;
import com.example.planning_worker.repository.result.PaginateExchange;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCurrencyExchangeRateRepository {
    List<PaginateExchange> getMonthYearPaginated(Integer year, Pageable pageable);

    List<CurrencyExchangeRate> getListCurrencyExchangeRateByMonthYear(List<PaginateExchange> paginateExchanges);

    List<CurrencyExchangeRate> getListCurrencyExchangeRateByMonthYear(List<PaginateExchange> paginateExchanges, List<Long> currencyIds);

    long countDistinctListExchangePaging(Integer year);
}
