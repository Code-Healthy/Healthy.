package com.healthy.api;

import com.healthy.model.entity.Expert;
import com.healthy.service.AdminExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/experts")
public class AdminExpertController {
    private final AdminExpertService adminExpertService;

    @GetMapping
    public ResponseEntity<List<Expert>> listAll() {
        List<Expert> experts = adminExpertService.getAll();
        return new ResponseEntity<>(experts, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Expert>> paginate(
            @PageableDefault(size = 5, sort = "firstName")Pageable pageable) {
        Page<Expert> page = adminExpertService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expert> getById(@PathVariable Integer id) {
        Expert expert = adminExpertService.findById(id);
        return new ResponseEntity<>(expert, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expert> create(@RequestBody Expert expert) {
        Expert createdExpert = adminExpertService.create(expert);
        return new ResponseEntity<>(createdExpert, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expert> update(@PathVariable Integer id,
                                                     @RequestBody Expert expert) {
        Expert updatedExpert = adminExpertService.update(id,expert);
        return new ResponseEntity<>(updatedExpert, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminExpertService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
