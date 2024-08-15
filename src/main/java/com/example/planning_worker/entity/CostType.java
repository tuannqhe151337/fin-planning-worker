package com.example.planning_worker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "capstone_v2",name = "cost_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class CostType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = Report_.COST_TYPE)
    private List<Report> reports;

    @OneToMany(mappedBy = FinancialPlanExpense_.COST_TYPE)
    private List<FinancialPlanExpense> planExpenses;

    @Column(name = "is_delete",columnDefinition = "bit default 0")
    private boolean isDelete;

}
