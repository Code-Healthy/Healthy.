package com.healthy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Habit;
import java.util.List;

public interface AdminHabitService {
    List<Habit> getAll();
    Page<Habit> paginate(Pageable pageable);
    Habit findById(int id);
    Habit create(Habit habit);
    Habit update(Integer id, Habit updateHabit);
    void delete(Integer id);
}