package com.healthy.service;

import com.healthy.model.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminSubscriptionService {
    List<Subscription> getAll();
    Page<Subscription> paginate(Pageable pageable);
    Subscription findById(Integer id);
    Subscription create(Subscription subscription);
    Subscription update(Integer id, Subscription updateSubscription);

    void delete(Integer id);

}
