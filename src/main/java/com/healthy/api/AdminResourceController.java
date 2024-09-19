package com.healthy.api;


import com.healthy.dto.ResourceDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import java.util.List;
import com.healthy.model.entity.Resource;
import com.healthy.service.AdminResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/resources")
public class AdminResourceController {
    private final AdminResourceService adminResourceService;


    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        List<ResourceDTO> resources = adminResourceService.getAll();
        return new ResponseEntity<>(resources, HttpStatus.OK);

    }


    @GetMapping("/page")
    public ResponseEntity<Page<ResourceDTO>> paginateResources(
            @PageableDefault (size =5, sort ="title")Pageable pageable) {
        Page<ResourceDTO> resources = adminResourceService.paginate(pageable);
        return new ResponseEntity<>(resources, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable("id") Integer id) {
        ResourceDTO resource = adminResourceService.findById(id);
        return new ResponseEntity<>(resource,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ResourceDTO> create(@Valid @RequestBody ResourceDTO resourceDTO) {
        ResourceDTO createdResource = adminResourceService.create(resourceDTO);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable("id") Integer id,
                                                   @Valid @RequestBody ResourceDTO resourceDTO) {
        ResourceDTO updateResource = adminResourceService.update(id, resourceDTO);
        return new ResponseEntity<>(updateResource, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Integer id) {
       adminResourceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
