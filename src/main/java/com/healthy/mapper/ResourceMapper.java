package com.healthy.mapper;

import com.healthy.dto.ResourceCreateUpdateDTO;
import com.healthy.dto.ResourceDTO;
import com.healthy.model.entity.Resource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    private final ModelMapper modelMapper;
    public ResourceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ResourceDTO toDTO(Resource resource) {
       ResourceDTO resourceDTO = modelMapper.map(resource, ResourceDTO.class);
       resourceDTO.setFirstName(resource.getExpert().getFirstName());
       resourceDTO.setLastName(resource.getExpert().getLastName());
       resourceDTO.setExpertise(resource.getExpert().getExpertise());
       return resourceDTO;
    }

    public Resource toEntity(ResourceCreateUpdateDTO resourceDTO) {
        return modelMapper.map(resourceDTO, Resource.class);
    }
}
