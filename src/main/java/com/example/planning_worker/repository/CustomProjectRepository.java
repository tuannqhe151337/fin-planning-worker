package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomProjectRepository {

    List<Project> getProjectWithPagination(String query, Pageable pageable);
}
