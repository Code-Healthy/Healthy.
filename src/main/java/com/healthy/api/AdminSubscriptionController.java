package com.healthy.api;

import com.healthy.dto.SubscriptionCreateUpdateDTO;
import com.healthy.dto.SubscriptionDetailsDTO;
import com.healthy.service.AdminSubscriptionService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/subscriptions")
public class AdminSubscriptionController {
    private final AdminSubscriptionService adminSubscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionDetailsDTO>> getAll() {
        List<SubscriptionDetailsDTO> subscription = adminSubscriptionService.getAll();
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SubscriptionDetailsDTO>> paginate(
            @PageableDefault(size=5) Pageable pageable)
    {
        Page<SubscriptionDetailsDTO> subscription = adminSubscriptionService.paginate(pageable);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDetailsDTO> getById(@PathVariable Integer id) {
        SubscriptionDetailsDTO subscription = adminSubscriptionService.findById(id);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubscriptionDetailsDTO> create(@Valid @RequestBody SubscriptionCreateUpdateDTO subscriptionFromDto) {
        SubscriptionDetailsDTO newSubscription = adminSubscriptionService.create(subscriptionFromDto);
        return new ResponseEntity<>(newSubscription, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDetailsDTO> update(@PathVariable Integer id,
                                                         @Valid @RequestBody SubscriptionCreateUpdateDTO subscriptionFromDto) {
        SubscriptionDetailsDTO updatedSubscription = adminSubscriptionService.update(id, subscriptionFromDto);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriptionDetailsDTO> delete(@PathVariable("id") Integer id) {
        adminSubscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
