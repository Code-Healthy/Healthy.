package com.healthy.api;

import com.healthy.model.entity.Goal;
import com.healthy.service.AdminGoalService;
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
    public ResponseEntity<List<Goal>> getAllGoals(){
        List<Goal> goals = adminGoalService.getAll();
        return new ResponseEntity<List<Goal>>(goals,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Goal>> paginateGoals(
            @PageableDefault (size=5, sort="startDate")Pageable pageable)
    {
        Page<Goal> goals = adminGoalService.paginate(pageable);
        return new ResponseEntity<Page<Goal>>(goals,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable("id") Integer id){
        Goal goal = adminGoalService.findById(id);
        return new ResponseEntity<Goal>(goal,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal){
        Goal newGoal = adminGoalService.create(goal);
        return new ResponseEntity<Goal>(newGoal,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal( @PathVariable("id") Integer id,
                                            @RequestBody Goal goal){
        Goal updatedGoal = adminGoalService.update(id, goal);
        return new ResponseEntity<Goal>(updatedGoal,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable("id") Integer id){
        adminGoalService.delete(id);
        return new ResponseEntity<Goal>(HttpStatus.NO_CONTENT);
    }
}
