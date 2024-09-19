package com.healthy.service.impl;

import com.healthy.model.entity.Plan;
import com.healthy.repository.PlanRepository;
import com.healthy.service.AdminPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPlanServiceImpl implements AdminPlanService {

    private final PlanRepository planRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Plan> paginate(Pageable pageable) {
        return planRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Plan findById(int id) {
        return planRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    @Transactional
    @Override
    public Plan create(Plan plan) {
        plan.setStartDate(LocalDateTime.now());
        return planRepository.save(plan);
    }

    @Transactional
    @Override
    public Plan update(Integer id, Plan updatePlan) {
        Plan planFromDb = findById(id);
        planFromDb.setName(updatePlan.getName());
        planFromDb.setDescription(updatePlan.getDescription());
        planFromDb.setStartDate(updatePlan.getStartDate());
        planFromDb.setEndDate(updatePlan.getEndDate());
        planFromDb.setId(updatePlan.getId());
        planFromDb.setPlanStatus(updatePlan.getPlanStatus());
        planFromDb.setUser(updatePlan.getUser());
        planFromDb.setGoals(updatePlan.getGoals());
        return planRepository.save(planFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Plan plan = findById(id);
        planRepository.delete(plan);
    }
}
