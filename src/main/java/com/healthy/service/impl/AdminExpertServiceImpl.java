package com.healthy.service.impl;

import com.healthy.model.entity.Expert;
import com.healthy.model.entity.HabitType;
import com.healthy.repository.ExpertRepository;
import com.healthy.service.AdminExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminExpertServiceImpl implements AdminExpertService {
    private final ExpertRepository expertRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Expert> getAll() {
        return expertRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Expert> paginate(Pageable pageable) {
        return expertRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Expert findById(Integer id) {
        return expertRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Expert not found"));
    }

    @Transactional
    @Override
    public Expert create(Expert expert) {
        expert.setCreatedAt(LocalDateTime.now());
        return expertRepository.save(expert);
    }

    @Transactional
    @Override
    public Expert update(Integer id, Expert updateExpert) {
        Expert expertFromDb = findById(id);
        expertFromDb.setFirstName(updateExpert.getFirstName());
        expertFromDb.setLastName(updateExpert.getLastName());
        expertFromDb.setBio(updateExpert.getBio());
        expertFromDb.setUpdatedAt(LocalDateTime.now());
        return expertRepository.save(expertFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Expert expert = findById(id);
        expertRepository.delete(expert);
    }
}