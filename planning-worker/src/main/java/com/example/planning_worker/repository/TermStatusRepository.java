package com.example.planning_worker.repository;

import com.example.planning_worker.entity.TermStatus;
import com.example.planning_worker.utils.enums.TermCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermStatusRepository extends JpaRepository<TermStatus, Long> {
    @Override
    List<TermStatus> findAll();

    @Override
    <S extends TermStatus> List<S> saveAll(Iterable<S> entities);

    TermStatus findByCode(TermCode termCode);
}
