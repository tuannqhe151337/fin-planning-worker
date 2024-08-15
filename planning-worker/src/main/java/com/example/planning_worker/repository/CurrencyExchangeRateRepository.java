package com.example.planning_worker.repository;

import com.example.planning_worker.entity.CurrencyExchangeRate;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, Long>, CustomCurrencyExchangeRateRepository {
//    @Query( " SELECT count(distinct(exchangeRate)) FROM CurrencyExchangeRate exchangeRate " +
//            " WHERE (year(exchangeRate.month) = :year OR :year is null )" +
//            " GROUP BY year(exchangeRate.month), month(exchangeRate.month)")
//    long countDistinctListExchangePaging(Integer year);

    @Modifying
    @Query("UPDATE CurrencyExchangeRate currencyExchangeRate " +
            "SET currencyExchangeRate.isDelete = true " +
            "WHERE year(currencyExchangeRate.month) = :year AND month(currencyExchangeRate.month) = :month ")
    void deleteCurrencyExchangeRateIdByMonth(@Param("month") Integer month, @Param("year") Integer year);
}
