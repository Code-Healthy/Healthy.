package com.healthy.repository;

import com.healthy.model.entity.HabitType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitTypeRepository extends JpaRepository<HabitType, Integer> {

}