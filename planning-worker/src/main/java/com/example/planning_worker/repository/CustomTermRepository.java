package com.example.planning_worker.repository;


import com.example.planning_worker.entity.Term;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomTermRepository {
    List<Term> getListTermWhenCreatePlan(String query, Pageable pageable, Long departmentId);
    List<Term> getListTermPaging(Long statusID, String query, Pageable pageable);



}
