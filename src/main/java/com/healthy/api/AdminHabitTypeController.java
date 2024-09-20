package com.healthy.api;

import com.healthy.model.entity.HabitType;
import com.healthy.service.AdminHabitTypeService;
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
@RequestMapping("/admin/habitTypes")
public class AdminHabitTypeController {
    private final AdminHabitTypeService adminHabitTypeService;

    @GetMapping
    public ResponseEntity<List<HabitType>> getAllHabitTypes() {
        List<HabitType> habitTypes = adminHabitTypeService.getAll();
        return new ResponseEntity<List<HabitType>>(habitTypes, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<HabitType>> paginateHabitTypes(
            @PageableDefault(size = 5, sort = "name")Pageable pageable) {
        Page<HabitType> habitTypes = adminHabitTypeService.paginate(pageable);
        return new ResponseEntity<Page<HabitType>>(habitTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitType> getHabitTypeById(@PathVariable("id") Integer id) {
        HabitType habitType = adminHabitTypeService.findById(id);
        return new ResponseEntity<HabitType>(habitType, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HabitType> createHabitType(@RequestBody HabitType habitType) {
        HabitType newHabitType = adminHabitTypeService.create(habitType);
        return new ResponseEntity<HabitType>(newHabitType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitType> updateHabitType(@PathVariable("id") Integer id,
                                                     @RequestBody HabitType habitType) {
        HabitType updateHabitType = adminHabitTypeService.update(id,habitType);
        return new ResponseEntity<HabitType>(updateHabitType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HabitType> deleteHabitType(@PathVariable("id") Integer id) {
        adminHabitTypeService.delete(id);
        return new ResponseEntity<HabitType>(HttpStatus.NO_CONTENT);
    }
    /* */
}