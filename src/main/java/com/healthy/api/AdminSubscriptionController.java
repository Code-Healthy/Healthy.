package com.healthy.api;


import com.healthy.model.entity.Subscription;
import com.healthy.service.AdminSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/subscriptions")
public class AdminSubscriptionController {
    private final AdminSubscriptionService adminSubscriptionService;


    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = adminSubscriptionService.getAll();
        return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Subscription>> paginateSubscriptions(
            @PageableDefault (size =5) Pageable pageable) {
        Page<Subscription> subscriptions = adminSubscriptionService.paginate(pageable);
        return new ResponseEntity<Page<Subscription>>(subscriptions, HttpStatus.OK);

    }



    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable("id")Integer id) {
        Subscription subscription = adminSubscriptionService.findById(id);
        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription newSubscription = adminSubscriptionService.create(subscription);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") Integer id,
                                                           @RequestBody  Subscription subscription) {
        Subscription updateSubscription = adminSubscriptionService.create(subscription);
        return new ResponseEntity<>(updateSubscription, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable("id")Integer id){
        adminSubscriptionService.delete(id);
        return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
    }



}
