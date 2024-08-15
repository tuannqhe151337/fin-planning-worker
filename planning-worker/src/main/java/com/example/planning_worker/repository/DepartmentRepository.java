package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Long>  {
    @Query(value = "select count(distinct(department.id)) from Department department " +
            "where department.name like %:query% and (department.isDelete = false or department.isDelete is null)")
    long countDistinct(String query);

    boolean existsById(Long id);
    @Query(value = " SELECT DISTINCT department.id FROM Department department " +
            " JOIN department.plans plan " +
            " JOIN plan.planFiles file " +
            " WHERE file.id = :fileId AND " +
            " file.isDelete = false ")
    long getDepartmentIdByFileId(Long fileId);

    @Query(value = " SELECT DISTINCT department.id FROM Department department " +
            " JOIN department.plans plan " +
            " WHERE plan.id = :planId AND " +
            " plan.isDelete = false AND department.isDelete = false ")
    long getDepartmentIdByPlanId(Long planId);
}