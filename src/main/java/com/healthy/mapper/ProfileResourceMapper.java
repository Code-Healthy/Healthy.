package com.healthy.mapper;

import com.healthy.dto.ProfileResourceCreateUpdateDTO;
import com.healthy.dto.ProfileResourceDetailsDTO;
import com.healthy.model.entity.ProfileResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProfileResourceMapper {
    private final ModelMapper modelMapper;

    public ProfileResourceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ProfileResourceDetailsDTO toDetailsDTO(ProfileResource profileResource) {
        ProfileResourceDetailsDTO profileResourceDetailsDTO = modelMapper.map(profileResource, ProfileResourceDetailsDTO.class);

        profileResourceDetailsDTO.setResourceTittle(profileResource.getResource().getTitle());
        profileResourceDetailsDTO.setResourceDescription(profileResource.getResource().getDescription());

        profileResourceDetailsDTO.setExpertFirstName(profileResource.getResource().getExpert().getFirstName());
        profileResourceDetailsDTO.setExpertLastName(profileResource.getResource().getExpert().getLastName());
        profileResourceDetailsDTO.setExpertExpertise(profileResource.getResource().getExpert().getExpertise());

        profileResourceDetailsDTO.setSubPlanName(profileResource.getResource().getSubPlan().getName());

        profileResourceDetailsDTO.setUserName(profileResource.getProfile().getUserName());

        return profileResourceDetailsDTO;
    }

    public ProfileResource toProfileResource(ProfileResourceCreateUpdateDTO resourceCreateUpdateDTO) {
        return modelMapper.map(resourceCreateUpdateDTO, ProfileResource.class);
    }

    public ProfileResourceCreateUpdateDTO toProfileResourceCreateUpdateDTO(ProfileResource profileResource) {
        return modelMapper.map(profileResource, ProfileResourceCreateUpdateDTO.class);
    }
}
