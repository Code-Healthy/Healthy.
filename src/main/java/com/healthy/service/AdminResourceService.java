package com.healthy.service;
import java.util.List;
import com.healthy.model.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminResourceService {
    List<Resource> getAll();
    Page<Resource> paginate(Pageable pageable);
    Resource findById(Integer id);
    Resource create(Resource resource);
    Resource update(Integer id, Resource updateResource);
    void delete(Integer id);
}
