package com.healthy.service;

import com.healthy.model.entity.HabitType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminHabitTypeService {
    List<HabitType> getAll();
    Page<HabitType> paginate(Pageable pageable);
    HabitType findById(Integer id);
    HabitType create(HabitType habitType);
    HabitType update(Integer id, HabitType updateHabitType);
    void delete(Integer id);
}
