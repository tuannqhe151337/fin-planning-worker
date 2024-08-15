package com.example.planning_worker.repository;

import com.example.planning_worker.entity.Position;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPositionRepository {

   List<Position> getPositionWithPagination(String query, Pageable pageable) ;
}
