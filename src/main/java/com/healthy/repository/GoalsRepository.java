package com.healthy.repository;
import com.healthy.model.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalsRepository extends JpaRepository<Goal, Integer> {
}