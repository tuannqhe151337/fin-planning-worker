package com.example.planning_worker.entity;

import com.example.planning_worker.utils.enums.ReportStatusCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "capstone_v2",name = "report_status")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class ReportStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    @Enumerated(EnumType.STRING)
    private ReportStatusCode code;

    @OneToMany(mappedBy = FinancialReport_.STATUS)
    private List<FinancialReport> financialPlans;

    @Column(name = "is_delete",columnDefinition = "bit default 0")
    private boolean isDelete;
}
