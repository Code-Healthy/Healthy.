package com.healthy.mapper;

import com.healthy.dto.AuthResponseDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.dto.UserRegistrationDTO;
import com.healthy.model.entity.User;
import com.healthy.model.entity.Profile;
import com.healthy.model.enums.Gender;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }


    public UserRegistrationDTO toUserRegistrationDTO(User user) {
        UserRegistrationDTO userRegistrationDTO = modelMapper.map(user, UserRegistrationDTO.class);

        if (user.getProfile() != null) {
            userRegistrationDTO.setUserName(user.getUserName());
            userRegistrationDTO.setPassword(user.getPassword());
            //userRegistrationDTO.setHeight(user.getProfile().getHeight());
            //userRegistrationDTO.setWeight(user.getProfile().getWeight());
            //userRegistrationDTO.setAge(user.getProfile().getAge());
            //userRegistrationDTO.setGender(user.getProfile().getGender());
            //userRegistrationDTO.setHealthConditions(user.getProfile().getHealthConditions());
        }

        return userRegistrationDTO;

    }
    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);
        authResponseDTO.setUserName(user.getUserName());
        authResponseDTO.setRole(user.getRole().getName().name());
        return authResponseDTO;
    }





}