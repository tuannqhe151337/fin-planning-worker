package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Department;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomDepartmentRepository {
   List<Department> getDepartmentWithPagination(String query, Pageable pageable) ;

}