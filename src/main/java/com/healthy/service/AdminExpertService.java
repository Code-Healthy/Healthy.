package com.healthy.service;

import com.healthy.model.entity.Expert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminExpertService {
    List<Expert> getAll();
    Page<Expert> paginate(Pageable pageable);
    Expert findById(Integer id);
    Expert create(Expert Expert);
    Expert update(Integer id, Expert updateExpert);
    void delete(Integer id);
}
