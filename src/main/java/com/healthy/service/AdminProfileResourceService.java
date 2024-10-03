package com.healthy.service;

import java.util.List;

import com.healthy.dto.ProfileResourceCreateUpdateDTO;
import com.healthy.dto.ProfileResourceDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminProfileResourceService {
    List<ProfileResourceDetailsDTO> getAll();
    Page<ProfileResourceDetailsDTO> paginate(Pageable pageable);
    ProfileResourceDetailsDTO findById(Integer id);
    ProfileResourceDetailsDTO create(ProfileResourceCreateUpdateDTO profileResourceCreateUpdateDTO);
    ProfileResourceDetailsDTO update(Integer id, ProfileResourceCreateUpdateDTO profileResourceCreateUpdateDTO);
    void delete(Integer id);
}
