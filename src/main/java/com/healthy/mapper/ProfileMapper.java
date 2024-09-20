package com.healthy.mapper;

import com.healthy.dto.ProfileCreateUpdateDTO;
import com.healthy.dto.ProfileDetailsDTO;
import com.healthy.model.entity.Profile;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    private final ModelMapper modelMapper;

    public ProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    public ProfileDetailsDTO toDetailsDTO(Profile profile) {
        ProfileDetailsDTO profileDetailsDTO = modelMapper.map(profile, ProfileDetailsDTO.class);
        profileDetailsDTO.setUserName(profile.getUser().getUsername());
        return profileDetailsDTO;

    }
    public Profile toEntity(ProfileCreateUpdateDTO profileCreateUpdateDTO) {
        return modelMapper.map(profileCreateUpdateDTO, Profile.class);
    }
    public ProfileCreateUpdateDTO toCreateUpdateDTO(Profile profile) {
        return modelMapper.map(profile, ProfileCreateUpdateDTO.class);
    }

}