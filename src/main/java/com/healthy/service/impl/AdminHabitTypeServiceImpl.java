package com.healthy.service.impl;

import com.healthy.model.entity.HabitType;
import com.healthy.repository.HabitTypeRepository;
import com.healthy.service.AdminHabitTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminHabitTypeServiceImpl implements AdminHabitTypeService {

    private final HabitTypeRepository habitTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<HabitType> getAll() {
        return habitTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<HabitType> paginate(Pageable pageable) {
        return habitTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public HabitType findById(Integer id) {
        return habitTypeRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Type not found"));
    }

    @Transactional
    @Override
    public HabitType create(HabitType habitType) {
        habitType.setCreatedAt(LocalDateTime.now());
        return habitTypeRepository.save(habitType);
    }

    @Transactional
    @Override
    public HabitType update(Integer id, HabitType updateHabitType) {
        HabitType habitTypeFromDb = findById(id);
        habitTypeFromDb.setName(updateHabitType.getName());
        habitTypeFromDb.setDescription(updateHabitType.getDescription());
        habitTypeFromDb.setUpdatedAt(LocalDateTime.now());
        return habitTypeRepository.save(habitTypeFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        HabitType habitType = findById(id);
        habitTypeRepository.deleteById(id);
    }
}
