package com.healthy.api;


import com.healthy.model.entity.Plan;
import com.healthy.service.AdminPlanService;
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
@RequestMapping("/admin/plans")
public class AdminPlanController {
    private final AdminPlanService adminPlanService;

    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlans(){
        List<Plan> plans = adminPlanService.getAll();
        return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Plan>> paginatePlans(
            @PageableDefault(size=10, sort="name") Pageable pageable)
    {
        Page<Plan> plans = adminPlanService.paginate(pageable);
        return new ResponseEntity<Page<Plan>>(plans,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable("id") Integer id){
        Plan plan = adminPlanService.findById(id);
        return new ResponseEntity<Plan>(plan,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan){
        Plan newPlan = adminPlanService.create(plan);
        return new ResponseEntity<Plan>(newPlan,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable("id") Integer id,
                                           @RequestBody Plan plan){
        Plan updatedPlan = adminPlanService.update(id, plan);
        return new ResponseEntity<Plan>(updatedPlan,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> deletePlan(@PathVariable("id") Integer id){
        adminPlanService.delete(id);
        return new ResponseEntity<Plan>(HttpStatus.NO_CONTENT);
    }
}
