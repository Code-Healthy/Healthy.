package com.healthy.service.impl;

import com.healthy.model.entity.Resource;
import com.healthy.repository.ResourceRepository;
import com.healthy.service.AdminResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AdminResourceServiceImpl implements AdminResourceService {
    private final ResourceRepository resourceRepository;
    @Transactional(readOnly = true)

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Resource> paginate(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Resource create(Resource resource) {
        return resourceRepository.save(resource);
    }


    @Transactional(readOnly = true)
    @Override
    public Resource findById(Integer id) {
        return resourceRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    @Override
    public Resource update(Integer id, Resource updateResource) {
        Resource resourceFromDb = findById(id);
        resourceFromDb.setTitle(updateResource.getTitle());
        resourceFromDb.setDescription(updateResource.getDescription());
        resourceFromDb.setResourceType(updateResource.getResourceType());
        resourceFromDb.setContent(updateResource.getContent());
        resourceFromDb.setExpert(updateResource.getExpert());
        return resourceRepository.save(resourceFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Resource resource = findById(id);
        resourceRepository.delete(resource);
    }
}
