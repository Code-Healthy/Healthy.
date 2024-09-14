package com.healthy.repository;

import com.healthy.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
}
