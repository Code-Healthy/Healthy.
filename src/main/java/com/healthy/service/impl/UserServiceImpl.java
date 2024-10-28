package com.healthy.service.impl;

import com.healthy.dto.AuthResponseDTO;
import com.healthy.dto.LoginDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.dto.UserRegistrationDTO;
import com.healthy.exception.ResourceConflictException;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.exception.RoleNotFoundException;
import com.healthy.mapper.ProfileMapper;
import com.healthy.mapper.UserMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.Role;
import com.healthy.model.entity.User;
import com.healthy.model.enums.ERole;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.RoleRepository;
import com.healthy.repository.UserRepository;
import com.healthy.security.TokenProvider;
import com.healthy.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final ProfileMapper profileMapper;

    @Transactional
    @Override
    public UserRegistrationDTO registerUser(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.CUSTOMER);
    }


    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(UserProfileDTO userProfileDTO) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        Profile profile = profileRepository.findByUserUserName(currentUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for user: " + currentUsername));

        profile.getUser().setUserName(userProfileDTO.getUsername());

        profile.setHeight(userProfileDTO.getHeight());
        profile.setWeight(userProfileDTO.getWeight());
        profile.setAge(userProfileDTO.getAge());
        profile.setGender(userProfileDTO.getGender());
        profile.setHealthConditions(userProfileDTO.getHealthConditions());

        profileRepository.save(profile);

        return profileMapper.toUserProfileDTO(profile);
    }

    @Transactional
    @Override
    public UserProfileDTO getUserProfileById(Integer id){
        return null;
    }

    private UserRegistrationDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum) {
        boolean existsAsUser = userRepository.existsByUserName(registrationDTO.getUserName());

        if (existsAsUser) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo username");
        }

        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found"));

        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        User user = userMapper.toUserEntity(registrationDTO);
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.toUserRegistrationDTO(savedUser);
    }

    @Transactional
    @Override
    public AuthResponseDTO login(LoginDTO loginDTO){
        User user = userRepository.findByUserName(loginDTO.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Error: Username not found"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        String token = tokenProvider.createAccessToken(authentication);
        AuthResponseDTO response = userMapper.toAuthResponseDTO(user, token);
        return response;
    }
}