package com.healthy.service.impl;

import com.healthy.dto.SubscriptionCreateUpdateDTO;
import com.healthy.dto.SubscriptionDetailsDTO;
import com.healthy.exception.ResourceNotFoundException;
import com.healthy.mapper.SubscriptionMapper;
import com.healthy.model.entity.Profile;
import com.healthy.model.entity.SubPlan;
import com.healthy.model.entity.Subscription;
import com.healthy.repository.ProfileRepository;
import com.healthy.repository.SubPlanRepository;
import com.healthy.repository.SubscriptionRepository;
import com.healthy.service.AdminSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminSubscriptionServiceImpl implements AdminSubscriptionService {

    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;
    private final SubPlanRepository subPlanRepository;
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SubscriptionDetailsDTO> getAll() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(subscriptionMapper::toDetailsDTO)
                .toList();
    }

    @Override
    public Page<SubscriptionDetailsDTO> paginate(Pageable pageable) {
        return subscriptionRepository.findAll(pageable)
                .map(subscriptionMapper::toDetailsDTO);
    }

    @Override
    public SubscriptionDetailsDTO findById(Integer id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));
        return subscriptionMapper.toDetailsDTO(subscription);
    }

    @Transactional
    @Override
    public SubscriptionDetailsDTO create(SubscriptionCreateUpdateDTO subscriptionCreateUpdateDTO) {
        Profile profile = profileRepository.findById(subscriptionCreateUpdateDTO.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));

        SubPlan subPlan = subPlanRepository.findById(subscriptionCreateUpdateDTO.getSubPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));

        Subscription subscription = subscriptionMapper.toSubscription(subscriptionCreateUpdateDTO);
        subscription.setProfile(profile);
        subscription.setSubPlan(subPlan);
        return subscriptionMapper.toDetailsDTO(subscriptionRepository.save(subscription));
    }

    @Transactional
    @Override
    public SubscriptionDetailsDTO update(Integer id, SubscriptionCreateUpdateDTO subscriptionCreateUpdateDTO) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));

        Profile profileFromDb = profileRepository.findById(subscriptionCreateUpdateDTO.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));

        SubPlan subPlanFromDb = subPlanRepository.findById(subscriptionCreateUpdateDTO.getSubPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));

        subscription.setProfile(profileFromDb);
        subscription.setSubPlan(subPlanFromDb);
        subscription.setId(subscriptionCreateUpdateDTO.getId());
        subscription.setStartAt(subscriptionCreateUpdateDTO.getStartAt());
        subscription.setEndAt(subscriptionCreateUpdateDTO.getEndAt());
        subscription.setPaymentStatus(subscriptionCreateUpdateDTO.getPaymentStatus());
        subscription.setSubscriptionStatus(subscriptionCreateUpdateDTO.getSubscriptionStatus());
        return subscriptionMapper.toDetailsDTO(subscriptionRepository.save(subscription));
    }

    public void delete(Integer id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No encontrado"));
        subscriptionRepository.delete(subscription);
    }
}
