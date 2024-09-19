package com.healthy.service.impl;

import com.healthy.dto.ResourceDTO;
import com.healthy.mapper.ResourceMapper;
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
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class AdminResourceServiceImpl implements AdminResourceService {
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    @Transactional(readOnly = true)

    @Override
    public List<ResourceDTO> getAll() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream().map(resourceMapper::toDTO).toList();
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ResourceDTO> paginate(Pageable pageable) {
        Page<Resource> resources = resourceRepository.findAll(pageable);
        return resources.map(resourceMapper::toDTO);
    }

    @Transactional
    @Override
    public ResourceDTO create(ResourceDTO resourceDTO) {
            resourceRepository.findByTitle(resourceDTO.getTitle())
                    .ifPresent(existingResource -> {
                        throw new RuntimeException("El recurso ya existe con el mismo nombre");

                    });

            Resource resource = resourceMapper.toEntity(resourceDTO);
            resource = resourceRepository.save(resource);
        return resourceMapper.toDTO(resource);
    }


    @Transactional(readOnly = true)
    @Override
    public ResourceDTO findById(Integer id) {
        Resource resource = resourceRepository.findById(id).orElseThrow(null);
        return resourceMapper.toDTO(resource);
    }

    @Transactional
    @Override
    public ResourceDTO update(Integer id, ResourceDTO updateResourceDTO) {
        Resource resourceFromDb = resourceRepository.findById(id).orElseThrow(null);


        resourceRepository.findByTitle(updateResourceDTO.getTitle())
                .filter(existingResource -> !existingResource.getTitle().equals(updateResourceDTO.getTitle()))
                .ifPresent(existingResource -> {
                    throw new RuntimeException("El recurso ya existe");
                });


        resourceFromDb.setTitle(updateResourceDTO.getTitle());
        resourceFromDb.setDescription(updateResourceDTO.getDescription());
        //resourceFromDb.setResourceType(updateResourceDTO.getResourceType());
        resourceFromDb.setContent(updateResourceDTO.getContent());
        //resourceFromDb.setExpert(updateResourceDTO.ge);

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
