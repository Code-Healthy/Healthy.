package com.healthy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Goal;
import java.util.List;

public interface AdminGoalService {
    List<Goal> getAll();
    Page<Goal> paginate(Pageable pageable);
    Goal findById(int id);
    Goal create(Goal goal);
    Goal update(Integer id, Goal updateGoal);
    void delete(Integer id);
}
