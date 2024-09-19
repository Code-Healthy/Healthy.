package com.healthy.mapper;

import com.healthy.dto.ResourceDTO;
import com.healthy.model.entity.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    private final ModelMapper modelMapper;
    public ResourceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResourceDTO toDTO(Resource resource) {
        return modelMapper.map(resource, ResourceDTO.class);
    }

    public Resource toEntity(ResourceDTO resourceDTO) {
        return modelMapper.map(resourceDTO, Resource.class);
    }
}
