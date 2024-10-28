package com.healthy.service;

import com.healthy.dto.ProfileCreateDTO;
import com.healthy.dto.ProfileDTO;
import com.healthy.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileService {

    List<ProfileDTO> getAll();
    Page<ProfileDTO> paginate(Pageable pageable);
    ProfileDTO create(ProfileCreateDTO profileCreateDTO);
    ProfileDTO findById(Integer id);
    void delete(Integer id);
}
