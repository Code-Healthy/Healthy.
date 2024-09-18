package com.healthy.api;

import com.healthy.model.entity.Habit;
import com.healthy.service.AdminHabitService;
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
@RequestMapping("/admin/habits")
public class AdminHabitController {
    private final AdminHabitService adminHabitService;

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits(){
        List<Habit> habits = adminHabitService.getAll();
        return new ResponseEntity<List<Habit>>(habits, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Habit>> paginateHabits(
            @PageableDefault(size=10, sort="name") Pageable pageable)
    {
        Page<Habit> habits = adminHabitService.paginate(pageable);
        return new ResponseEntity<Page<Habit>>(habits,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable("id") Integer id){
        Habit habit = adminHabitService.findById(id);
        return new ResponseEntity<Habit>(habit,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit){
        Habit newHabit = adminHabitService.create(habit);
        return new ResponseEntity<>(newHabit,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable("id") Integer id,
                                           @RequestBody Habit habit){
        Habit updatedHabit  = adminHabitService.update(id, habit);
        return new ResponseEntity<Habit>(updatedHabit,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Habit> deleteHabit(@PathVariable("id") Integer id){
        adminHabitService.delete(id);
        return new ResponseEntity<Habit>(HttpStatus.NO_CONTENT);
    }
}