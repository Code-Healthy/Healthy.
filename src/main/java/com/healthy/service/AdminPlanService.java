package com.healthy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Plan;
import java.util.List;

public interface AdminPlanService {
    List<Plan> getAll();
    Page<Plan> paginate(Pageable pageable);
    Plan findById(int id);
    Plan create(Plan plan);
    Plan update(Integer id, Plan updatePlan);
    void delete(Integer id);
}
