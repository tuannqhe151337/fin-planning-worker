package com.example.planning_worker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "capstone_v2", name = "financial_reports")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FinancialReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "month")
    private LocalDate month;

    @Column(name = "actual_cost")
    private BigDecimal actualCost;

    @Column(name = "expected_cost")
    private BigDecimal expectedCost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id")
    private ReportStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", unique = true)
    private Term term;

    @Column(name = "is_delete", columnDefinition = "bit default 0")
    private boolean isDelete;
}