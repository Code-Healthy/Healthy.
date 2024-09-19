package com.healthy.service;
import java.util.List;

import com.healthy.dto.ResourceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminResourceService {
    List<ResourceDTO> getAll();
    Page<ResourceDTO> paginate(Pageable pageable);
    ResourceDTO findById(Integer id);
    ResourceDTO create(ResourceDTO resourceDTO);
    ResourceDTO update(Integer id, ResourceDTO updateResourceDTO);
    void delete(Integer id);
}
