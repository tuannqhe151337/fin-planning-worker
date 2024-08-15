package com.example.planning_worker.service.result;

import com.example.planning_worker.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostResult {
    BigDecimal cost;
    Currency currency;
}
