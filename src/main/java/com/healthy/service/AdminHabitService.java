package com.healthy.service;

import com.healthy.dto.HabitCreateUpdateDTO;
import com.healthy.dto.HabitDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.Habit;
import java.util.List;

public interface AdminHabitService {
    List<HabitDetailsDTO> getAll();
    Page<HabitDetailsDTO> paginate(Pageable pageable);
    HabitDetailsDTO findById(int id);
    HabitDetailsDTO create(HabitCreateUpdateDTO habitCreateUpdateDTO);
    HabitDetailsDTO update(Integer id, HabitCreateUpdateDTO habitCreateUpdateDTO);
    void delete(Integer id);
}