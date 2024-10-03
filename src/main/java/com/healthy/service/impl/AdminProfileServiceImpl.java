package com.healthy.service.impl;

import com.healthy.dto.ProfileCreateUpdateDTO;
import com.healthy.dto.ProfileDetailsDTO;
import com.healthy.mapper.ProfileMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.User;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.UserRepository;
import com.healthy.service.AdminProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProfileServiceImpl implements AdminProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    @Transactional(readOnly = true)
    @Override
    public List<ProfileDetailsDTO> getAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::toDetailsDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProfileDetailsDTO> paginate(Pageable pageable){
        return profileRepository.findAll(pageable)
                .map(profileMapper::toDetailsDTO);
    }

    @Transactional
    @Override
    public ProfileDetailsDTO create(ProfileCreateUpdateDTO profileCreateUpdateDTO) {
        User user = userRepository.findById(profileCreateUpdateDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id: "+profileCreateUpdateDTO.getUserId()));

        Profile profile = profileMapper.toEntity(profileCreateUpdateDTO);
        profile.setUser(user);
        return profileMapper.toDetailsDTO(profileRepository.save(profile));
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileDetailsDTO findById(Integer id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with id: "+id));
        return profileMapper.toDetailsDTO(profile);
    }

    @Transactional
    @Override
    public ProfileDetailsDTO update(Integer id, ProfileCreateUpdateDTO updatedProfile) {

        Profile profileFromDb = profileRepository.findById(id).
                orElseThrow(()-> new RuntimeException("User not found with id: "+id));

        User user = userRepository.findById(updatedProfile.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id: "+updatedProfile.getUserId()));


        profileFromDb.setUser(user);
        profileFromDb.setAge(updatedProfile.getAge());
        profileFromDb.setGender(updatedProfile.getGender());
        profileFromDb.setHeight(updatedProfile.getHeight());
        profileFromDb.setWeight(updatedProfile.getWeight());
        profileFromDb.setHealthConditions(updatedProfile.getHealthConditions());

        return profileMapper.toDetailsDTO(profileRepository.save(profileFromDb));
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Profile not found with id: "+id));
        profileRepository.delete(profile);
    }
}