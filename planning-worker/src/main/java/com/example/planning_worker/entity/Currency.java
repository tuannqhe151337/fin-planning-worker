package com.example.planning_worker.entity;

import com.example.planning_worker.utils.enums.Affix;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "capstone_v2", name = "currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol", columnDefinition = "NVARCHAR(10)")
    private String symbol;

    @Column(name = "affrix")
    @Enumerated(EnumType.STRING)
    private Affix affix;

    @OneToMany(mappedBy = CurrencyExchangeRate_.CURRENCY)
    private List<CurrencyExchangeRate> currencyExchangeRates;

    @Column(name = "isDefault", updatable = false)
    private boolean isDefault;

    @Column(name = "is_delete", columnDefinition = "bit default 0")
    private boolean isDelete;
}
