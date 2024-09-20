package com.healthy.service;

import com.healthy.dto.GoalCreateUpdateDTO;
import com.healthy.dto.GoalDetailsDTO;
import com.healthy.dto.PlanCreateUpdateDTO;
import com.healthy.dto.PlanDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Plan;
import java.util.List;

public interface AdminPlanService {
    List<PlanDetailsDTO> getAll();
    Page<PlanDetailsDTO> paginate(Pageable pageable);
    PlanDetailsDTO findById(int id);
    PlanDetailsDTO create(PlanCreateUpdateDTO plan);
    PlanDetailsDTO update(Integer id, PlanCreateUpdateDTO updatePlanDTO);
    void delete(Integer id);
}

