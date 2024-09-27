package com.healthy.api;


import com.healthy.dto.PlanCreateUpdateDTO;
import com.healthy.dto.PlanDetailsDTO;
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
    public ResponseEntity<List<PlanDetailsDTO>> getAllPlans(){
        List<PlanDetailsDTO> plans = adminPlanService.getAll();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PlanDetailsDTO>> paginatePlans(
            @PageableDefault(size=10, sort="name") Pageable pageable)
    {
        Page<PlanDetailsDTO> plans = adminPlanService.paginate(pageable);
        return new ResponseEntity<>(plans,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDetailsDTO> getPlanById(@PathVariable("id") Integer id){
        PlanDetailsDTO plan = adminPlanService.findById(id);
        return new ResponseEntity<>(plan,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanDetailsDTO> createPlan(@RequestBody PlanCreateUpdateDTO plan){
        PlanDetailsDTO newPlan = adminPlanService.create(plan);
        return new ResponseEntity<>(newPlan,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDetailsDTO> updatePlan(@PathVariable("id") Integer id,
                                                     @RequestBody PlanCreateUpdateDTO plan){
        PlanDetailsDTO updatedPlan = adminPlanService.update(id, plan);
        return new ResponseEntity<>(updatedPlan,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> deletePlan(@PathVariable("id") Integer id){
        adminPlanService.delete(id);
        return new ResponseEntity<Plan>(HttpStatus.NO_CONTENT);
    }
}