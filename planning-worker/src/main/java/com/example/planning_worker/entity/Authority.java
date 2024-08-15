package com.example.planning_worker.entity;

import com.example.planning_worker.utils.enums.AuthorityCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(schema = "capstone_v2",name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Authority extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    private AuthorityCode code;

    @Column(name = "is_delete",columnDefinition = "bit default 0")
    private Boolean isDelete;

    @ToString.Exclude
    @OneToMany(mappedBy = "authority")
    private List<RoleAuthority> roleAuthorities;
}
