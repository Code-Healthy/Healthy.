package com.healthy.service.impl;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.UserProfileDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.SubPlanRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final SubPlanRepository subPlanRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProfileDTO> getAll(){
        List<Profile> profiles = profileRepository.findAll();

        return profiles.stream()
                .map(profileMapper::toProfileDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProfileDTO> paginate(Pageable pageable){
        return profileRepository.findAll(pageable)
                .map(profileMapper::toProfileDTO);
    }

    @Transactional
    @Override
    public ProfileDTO create(ProfileCreateDTO profileCreateDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        Profile profile = profileMapper.toEntity(profileCreateDTO);

        profile.setUser(user);
        profile.setUserName(username);
        return profileMapper.toProfileDTO(profileRepository.save(profile));
    }


    @Transactional(readOnly = true)
    @Override
    public ProfileDTO findById(Integer id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile "+id+" not found"));
        return profileMapper.toProfileDTO(profile);
    }




    @Transactional
    @Override
    public void delete(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile not found with id: "+id));
        profileRepository.delete(profile);
    }

}