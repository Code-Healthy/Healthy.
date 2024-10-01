package com.healthy.service;

import com.healthy.dto.PlanCreateUpdateDTO;
import com.healthy.dto.PlanDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminPlanService {
    List<PlanDetailsDTO> getAll();
    Page<PlanDetailsDTO> paginate(Pageable pageable);
    PlanDetailsDTO findById(int id);
    PlanDetailsDTO create(PlanCreateUpdateDTO plan);
    PlanDetailsDTO update(Integer id, PlanCreateUpdateDTO updatePlanDTO);
    void delete(Integer id);
}
