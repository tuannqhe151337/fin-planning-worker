package com.example.planning_worker.repository.result;

import java.math.BigDecimal;

public interface ExchangeRateResult {
    String getDate();

    BigDecimal getAmount();

    Long getCurrencyId();

}
