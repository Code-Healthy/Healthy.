package com.healthy.service.impl;

import com.healthy.dto.PlanDetailsDTO;
import com.healthy.mapper.PlanMapper;
import com.healthy.model.entity.Plan;
import com.healthy.repository.PlanRepository;
import com.healthy.repository.UserRepository;
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
    private final PlanMapper planMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PlanDetailsDTO> getAll() {
        List<Plan> plans = planRepository.findAll();

        return plans.stream()
                .map(planMapper::toDetailsDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PlanDetailsDTO> paginate(Pageable pageable) {
        return planRepository.findAll(pageable)
                .map(planMapper::toDetailsDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public PlanDetailsDTO findById(int id) {

        User user=userRepository.findById(plan)

        return null;
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
