package com.healthy.service.impl;

import com.healthy.dto.PlanCreateUpdateDTO;
import com.healthy.dto.PlanDetailsDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.PlanMapper;
import com.healthy.model.entity.Plan;
import com.healthy.model.entity.User;
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
        Plan plan = planRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan "+id+" not found"));
        return planMapper.toDetailsDTO(plan);
    }

    @Transactional
    @Override
    public PlanDetailsDTO create(PlanCreateUpdateDTO planDTO) {

        User user = userRepository.findById(planDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User "+planDTO.getUserId()+" not found"));

        Plan plan = planMapper.toEntity(planDTO);
        plan.setStartDate(LocalDateTime.now());
        plan.setUser(user);
        return planMapper.toDetailsDTO(planRepository.save(plan));
    }

    @Transactional
    @Override
    public PlanDetailsDTO update(Integer id, PlanCreateUpdateDTO updatePlan) {

        Plan planFromDb = planRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan "+id+" not found"));
        User user = userRepository.findById(updatePlan.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User "+updatePlan.getUserId()+" not found"));

        Plan plan = planMapper.toEntity(updatePlan);
        plan.setStartDate(planFromDb.getStartDate());
        planFromDb.setName(updatePlan.getName());
        planFromDb.setDescription(updatePlan.getDescription());
        planFromDb.setEndDate(updatePlan.getEndDate());
        planFromDb.setPlanStatus(updatePlan.getPlanStatus());
        planFromDb.setUser(user);
        planFromDb.setGoals(updatePlan.getGoals());
        return planMapper.toDetailsDTO(planRepository.save(planFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Plan plan = planRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plan "+id+" not found"));
        planRepository.delete(plan);
    }
}