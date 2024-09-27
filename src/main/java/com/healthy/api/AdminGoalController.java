package com.healthy.api;

import com.healthy.dto.GoalCreateUpdateDTO;
import com.healthy.dto.GoalDetailsDTO;
import com.healthy.model.entity.Goal;
import com.healthy.service.AdminGoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/goals")
public class AdminGoalController {
    private final AdminGoalService adminGoalService;

    @GetMapping
    public ResponseEntity<List<GoalDetailsDTO>> getAllGoals(){
        List<GoalDetailsDTO> goals = adminGoalService.getAll();
        return new ResponseEntity<>(goals,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<GoalDetailsDTO>> paginateGoals(
            @PageableDefault (size=5, sort="startDate")Pageable pageable)
    {
        Page<GoalDetailsDTO> goals = adminGoalService.paginate(pageable);
        return new ResponseEntity<>(goals,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GoalDetailsDTO> getGoalById(@PathVariable("id") Integer id){
        GoalDetailsDTO goal = adminGoalService.findById(id);
        return new ResponseEntity<>(goal,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoalDetailsDTO> createGoal(@Valid @RequestBody GoalCreateUpdateDTO goal){
        GoalDetailsDTO newGoal = adminGoalService.create(goal);
        return new ResponseEntity<>(newGoal,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalDetailsDTO> updateGoal( @Valid @PathVariable("id") Integer id,
                                                      @RequestBody GoalCreateUpdateDTO goal){
        GoalDetailsDTO updatedGoal = adminGoalService.update(id, goal);
        return new ResponseEntity<>(updatedGoal,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable("id") Integer id){
        adminGoalService.delete(id);
        return new ResponseEntity<Goal>(HttpStatus.NO_CONTENT);
    }
}