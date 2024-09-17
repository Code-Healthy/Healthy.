package com.healthy.service.impl;

import com.healthy.model.entity.Goal;
import com.healthy.repository.GoalRepository;
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

    @Transactional(readOnly = true)
    @Override
    public List<Goal> getAll() {
        return goalRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Goal> paginate(Pageable pageable) {
        return goalRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Goal create(Goal goal) {
        goal.setStartDate(LocalDateTime.now());
        return goalRepository.save(goal);
    }

    @Transactional
    @Override
    public Goal update(Integer id, Goal updateGoal) {
        Goal goalFromDb = findById(id);
        goalFromDb.setEndDate(updateGoal.getEndDate());
        goalFromDb.setGoalStatus(updateGoal.getGoalStatus());
        goalFromDb.setTargetValue(updateGoal.getTargetValue());
        goalFromDb.setCurrentValue(updateGoal.getCurrentValue());
        return goalRepository.save(goalFromDb);
    }

    @Transactional(readOnly = true)
    @Override
    public Goal findById(int id) {
        return goalRepository.findById(id).orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Goal goal = findById(id);
        goalRepository.delete(goal);
    }
}
