package com.healthy.service.impl;

import com.healthy.dto.ResourceCreateUpdateDTO;
import com.healthy.dto.ResourceDTO;
import com.healthy.mapper.ResourceMapper;
import com.healthy.model.entity.Expert;
import com.healthy.model.entity.Resource;
import com.healthy.repository.ExpertRepository;
import com.healthy.repository.ResourceRepository;
import com.healthy.service.AdminResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminResourceServiceImpl implements AdminResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final ExpertRepository expertRepository;
    @Transactional(readOnly = true)

    @Override
    public List<ResourceDTO> getAll() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream().map(resourceMapper::toDTO).toList();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ResourceDTO> paginate(Pageable pageable) {
        return resourceRepository.findAll(pageable).map(resourceMapper::toDTO);
    }

    @Transactional
    @Override
    public ResourceDTO create(ResourceCreateUpdateDTO resourceDTO) {
        Expert expert = expertRepository.findById(resourceDTO.getIdExpert())
                .orElseThrow(()->new RuntimeException("Expert not found white Id: "+resourceDTO.getIdExpert()));

        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource.setExpert(expert);
        return resourceMapper.toDTO(resourceRepository.save(resource));
    }


    @Transactional(readOnly = true)
    @Override
    public ResourceDTO findById(Integer id) {
        Resource resource = resourceRepository.findById(id).orElseThrow(()->new RuntimeException("Resource not found white Id "));
        return resourceMapper.toDTO(resource);
    }

    @Transactional
    @Override
    public ResourceDTO update(Integer id, ResourceCreateUpdateDTO updateResourceDTO) {
        Resource resourceFromDb = resourceRepository.findById(id).orElseThrow(null);
        Expert expert = expertRepository.findById(updateResourceDTO.getIdExpert()).orElse(null);

        resourceRepository.findByTitle(updateResourceDTO.getTitle())
                .filter(existingResource -> !existingResource.getTitle().equals(updateResourceDTO.getTitle()))
                .ifPresent(existingResource -> {
                    throw new RuntimeException("El recurso ya existe");
                });

        resourceFromDb.setTitle(updateResourceDTO.getTitle());
        resourceFromDb.setDescription(updateResourceDTO.getDescription());
        resourceFromDb.setResourceType(updateResourceDTO.getResourceType());
        resourceFromDb.setContent(updateResourceDTO.getContent());
        resourceFromDb.setExpert(expert);

        resourceFromDb = resourceRepository.save(resourceFromDb);
        return resourceMapper.toDTO(resourceFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Resource resource = resourceRepository.findById(id).orElseThrow(null);
        resourceRepository.delete(resource);
    }
}