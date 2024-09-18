package com.healthy.service.impl;

import com.healthy.model.entity.Habit;
import com.healthy.repository.HabitRepository;
import com.healthy.service.AdminHabitService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminHabitServiceImpl implements AdminHabitService {

    private final HabitRepository habitRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Habit> getAll() {
        return habitRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Habit> paginate(Pageable pageable) {
        return habitRepository.findAll(pageable);
    }

    @Override
    public Habit findById(int id) {
        return habitRepository.findById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
    }

    @Transactional
    @Override
    public Habit create(Habit habit) {
        return habitRepository.save(habit);
    }

    @Transactional
    @Override
    public Habit update(Integer id, Habit updateHabit) {
        Habit habitFromDb = findById(id);
        habitFromDb.setId(updateHabit.getId());
        habitFromDb.setHabitType(updateHabit.getHabitType());
        habitFromDb.setName(updateHabit.getName());
        habitFromDb.setDescription(updateHabit.getDescription());
        habitFromDb.setFrequency(updateHabit.getFrequency());
        return habitRepository.save(habitFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        habitRepository.delete(habit);
    }
    /*Para poder eliminar un ID de Hábito, primero tengo que elimnar desde pgAdmin, en la tabla Goals*/
    /* DELETE FROM goals where habit_id = 207 */
    /*207 = id de hábito como ejemplo*/
}