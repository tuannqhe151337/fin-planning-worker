package com.example.planning_worker.repository.result;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserDataOption {
    private boolean ignoreFullName;
    private boolean ignoreUsername;
    private boolean ignoreEmail;
    private boolean ignoreDepartment;
    private boolean ignorePosition;
    private boolean ignoreRole;
}
