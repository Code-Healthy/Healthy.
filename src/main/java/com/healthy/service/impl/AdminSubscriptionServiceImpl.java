package com.healthy.service.impl;

import java.util.List;

import com.healthy.model.entity.Subscription;
import com.healthy.repository.SubscriptionRepository;
import com.healthy.service.AdminSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminSubscriptionServiceImpl implements AdminSubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Subscription> paginate(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Subscription findById(Integer id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Transactional
    @Override
    public Subscription update(Integer id, Subscription updateSubscription) {
        Subscription subscriptionFromDb = findById(id);
        subscriptionFromDb.setSubscriptionStatus(updateSubscription.getSubscriptionStatus());
        subscriptionFromDb.setStartDate(updateSubscription.getStartDate());
        subscriptionFromDb.setEndDate(updateSubscription.getEndDate());
        subscriptionFromDb.setPaymentStatus(updateSubscription.getPaymentStatus());
        subscriptionFromDb.setUser(updateSubscription.getUser());
        subscriptionFromDb.setResource(updateSubscription.getResource());
        return subscriptionRepository.save(subscriptionFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Subscription subscription = findById(id);
        subscriptionRepository.delete(subscription);

    }
}