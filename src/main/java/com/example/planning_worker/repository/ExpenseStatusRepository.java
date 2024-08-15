package com.example.planning_worker.repository;

import com.example.planning_worker.entity.ExpenseStatus;
import com.example.planning_worker.utils.enums.ExpenseStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseStatusRepository extends JpaRepository<ExpenseStatus, Long> {
    ExpenseStatus findByCode(ExpenseStatusCode expenseStatusCode);

    ExpenseStatus getReferenceByCode(ExpenseStatusCode code);
}
