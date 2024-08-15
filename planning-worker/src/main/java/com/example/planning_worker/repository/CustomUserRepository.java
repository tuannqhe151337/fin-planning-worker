package com.example.planning_worker.repository;

import com.example.planning_worker.entity.User;
import com.example.planning_worker.repository.result.UpdateUserDataOption;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserRepository {
    List<User> getUserWithPagination
            (Long roleId, Long departmentId,
             Long positionId, String query, Pageable pageable);

    void saveUserData(User user, UpdateUserDataOption option);
}
