package com.healthy.service;

import com.healthy.dto.GoalCreateUpdateDTO;
import com.healthy.dto.GoalDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Goal;
import java.util.List;

public interface AdminGoalService {
    List<GoalDetailsDTO> getAll();
    Page<GoalDetailsDTO> paginate(Pageable pageable);
    GoalDetailsDTO findById(int id);
    GoalDetailsDTO create(GoalCreateUpdateDTO goal);
    GoalDetailsDTO update(Integer id, GoalCreateUpdateDTO updateGoalDTO);
    void delete(Integer id);
}