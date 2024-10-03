package com.healthy.service.impl;

import com.healthy.dto.GoalCreateUpdateDTO;
import com.healthy.dto.GoalDetailsDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.GoalMapper;
import com.healthy.model.entity.*;
import com.healthy.repository.*;
import com.healthy.service.AdminGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminGoalServiceImpl implements AdminGoalService {

    private final GoalRepository goalRepository;
    private final ProfileRepository profileRepository;
    private final HabitRepository habitRepository;
    private final PlanRepository planRepository;
    private final GoalMapper goalMapper;


    @Transactional(readOnly = true)
    @Override
    public List<GoalDetailsDTO> getAll() {
        List<Goal> goals = goalRepository.findAll();

        return goals.stream()
                .map(goalMapper::toDetailsDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GoalDetailsDTO> paginate(Pageable pageable) {
        return goalRepository.findAll(pageable)
                .map(goalMapper::toDetailsDTO);
    }

    @Transactional
    @Override
    public GoalDetailsDTO create(GoalCreateUpdateDTO goalCreateUpdateDTO) {
        Profile profile = profileRepository.findById(goalCreateUpdateDTO.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("User "+goalCreateUpdateDTO.getProfileId()+" not found"));
        Habit habit = habitRepository.findById(goalCreateUpdateDTO.getHabitId()).
                orElseThrow(() -> new ResourceNotFoundException("Habit "+goalCreateUpdateDTO.getHabitId()+" not found"));
        Plan plan =planRepository.findById(goalCreateUpdateDTO.getPlanId()).
                orElseThrow(() -> new ResourceNotFoundException("Plan "+goalCreateUpdateDTO.getPlanId()+" not found"));

        Goal goal = goalMapper.toEntity(goalCreateUpdateDTO);

        goal.setStartDate(LocalDateTime.now());
        goal.setProfile(profile);
        goal.setHabit(habit);
        goal.setPlan(plan);
        return goalMapper.toDetailsDTO(goalRepository.save(goal));
    }

    @Transactional
    @Override
    public GoalDetailsDTO update(Integer id, GoalCreateUpdateDTO updateGoal) {

        Goal goalFromDb = goalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goal "+id+" not found"));

        Profile profile  = profileRepository.findById(updateGoal.getProfileId()).orElseThrow(() -> new ResourceNotFoundException("User "+updateGoal.getProfileId() + " not found"));
        Habit habit = habitRepository.findById(updateGoal.getHabitId()).orElseThrow(() -> new ResourceNotFoundException("Habit "+updateGoal.getHabitId()+" not found"));
        Plan plan = planRepository.findById(updateGoal.getPlanId()).orElseThrow(() -> new ResourceNotFoundException("Plan "+updateGoal.getPlanId()+" not found"));

        Goal goal=goalMapper.toEntity(updateGoal);

        goal.setStartDate(goalFromDb.getStartDate());
        goalFromDb.setEndDate(updateGoal.getEndDate());
        goalFromDb.setGoalStatus(updateGoal.getGoalStatus());
        goalFromDb.setTargetValue(updateGoal.getTargetValue());
        goalFromDb.setCurrentValue(updateGoal.getCurrentValue());
        goalFromDb.setHabit(habit);
        goalFromDb.setPlan(plan);
        goalFromDb.setProfile(profile);

        return goalMapper.toDetailsDTO(goalRepository.save(goalFromDb));
    }

    @Transactional(readOnly = true)
    @Override
    public GoalDetailsDTO findById(int id) {

        Goal goal = goalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goal "+id+" not found"));
        return goalMapper.toDetailsDTO(goal);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Goal goal = goalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Goal "+id+" not found"));
        goalRepository.delete(goal);
    }
}