package com.healthy.service;

import com.healthy.dto.ProfileCreateUpdateDTO;
import com.healthy.dto.ProfileDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminProfileService {
    List<ProfileDetailsDTO> getAll();
    Page<ProfileDetailsDTO> paginate(Pageable pageable);
    ProfileDetailsDTO create(ProfileCreateUpdateDTO profileCreateUpdateDTO);
    ProfileDetailsDTO findById(Integer id);
    ProfileDetailsDTO update(Integer id, ProfileCreateUpdateDTO updateProfileDto);
    void delete(Integer id);

}
