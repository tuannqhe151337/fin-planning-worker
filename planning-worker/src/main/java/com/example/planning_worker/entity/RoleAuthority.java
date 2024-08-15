package com.example.planning_worker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "capstone_v2",name = "role_authority")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class RoleAuthority extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Column(name = "is_delete", columnDefinition = "bit default 0")
    private boolean isDelete;
}
