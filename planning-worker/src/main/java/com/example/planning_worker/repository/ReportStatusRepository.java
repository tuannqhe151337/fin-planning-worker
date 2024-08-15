package com.example.planning_worker.repository;

import com.example.planning_worker.entity.ReportStatus;
import com.example.planning_worker.utils.enums.ReportStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportStatusRepository extends JpaRepository<ReportStatus,Long> {
    ReportStatus findByCode(ReportStatusCode reportStatusCode);
}
