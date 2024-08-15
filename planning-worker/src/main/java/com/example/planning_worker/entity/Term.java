package com.example.planning_worker.entity;

import com.example.planning_worker.utils.enums.TermDuration;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "capstone_v2",name = "terms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
public class Term extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Duration cannot be empty")
    @Column(name = "duration")
    @Enumerated(EnumType.STRING)
    private TermDuration duration ;

    @NotNull(message = "Start date cannot be null")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
   // @Future(message = "End date must be in the future")
    @Column(name = "end_date")
    private LocalDateTime endDate;

  // @Future(message = "Re-upload start date must be in the future")
    @Column(name = "start_reupload_date")
    private LocalDateTime reuploadStartDate;

   // @Future(message = "Re-upload end date must be in the future")
    @Column(name = "end_reupload_date")
    private LocalDateTime reuploadEndDate;

    @NotNull(message = "Final end date cannot be null")
    //@Future(message = "Final end date must be in the future")
    @Column(name = "final_end_term")
    private LocalDateTime finalEndTermDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id")
    private TermStatus status; //trong day da co isDelete roi

    @OneToMany(mappedBy = "term", fetch = FetchType.LAZY)
    private List<FinancialPlan> financialPlans;

    @OneToMany(mappedBy = "term", fetch = FetchType.LAZY)
    private List<FinancialReport> financialReports;

    @Column(name = "is_delete", columnDefinition = "bit default 0")
    private boolean isDelete;

    @NotNull(message = "allowReupload cannot be null")
    @Column(name = "allow_reupload", columnDefinition = "bit default 0")
    private boolean allowReupload;

}
