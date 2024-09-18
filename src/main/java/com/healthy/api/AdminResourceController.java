package com.healthy.api;


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
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = adminResourceService.getAll();
        return new ResponseEntity<List<Resource>>(resources, HttpStatus.OK);

    }


    @GetMapping("/page")
    public ResponseEntity<Page<Resource>> paginateResources(
            @PageableDefault (size =5, sort ="title")Pageable pageable) {
        Page<Resource> resources = adminResourceService.paginate(pageable);
        return new ResponseEntity<Page<Resource>>(resources, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable("id") Integer id) {
        Resource resource = adminResourceService.findById(id);
        return new ResponseEntity<Resource>(resource,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource newResource = adminResourceService.create(resource);
        return new ResponseEntity<>(newResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable("id") Integer id,
                                                   @RequestBody Resource resource) {
        Resource updateResource = adminResourceService.create(resource);
        return new ResponseEntity<>(updateResource, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteResource(@PathVariable("id") Integer id) {
       adminResourceService.delete(id);
        return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
    }

}
