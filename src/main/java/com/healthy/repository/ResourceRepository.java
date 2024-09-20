package com.healthy.repository;

import com.healthy.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    Optional<Resource> findByTitle(String title);
}