package com.healthy.mapper;

import com.healthy.dto.SubscriptionCreateUpdateDTO;
import com.healthy.dto.SubscriptionDetailsDTO;
import com.healthy.model.entity.Subscription;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {
    private final ModelMapper modelMapper;

    public SubscriptionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public SubscriptionDetailsDTO toDetailsDTO(Subscription subscription) {
        SubscriptionDetailsDTO subscriptionDetailsDTO = modelMapper.map(subscription, SubscriptionDetailsDTO.class);

        subscriptionDetailsDTO.setStartAt(subscription.getStartAt());
        subscriptionDetailsDTO.setEndAt(subscription.getEndAt());
        subscriptionDetailsDTO.setPaymentStatus(subscription.getPaymentStatus());
        subscriptionDetailsDTO.setSubscriptionStatus(subscription.getSubscriptionStatus());

        subscriptionDetailsDTO.setUserName(subscription.getProfile().getUserName());

        subscriptionDetailsDTO.setSubPlanName(subscription.getSubPlan().getName());

        return subscriptionDetailsDTO;
    }

    public Subscription toSubscription(SubscriptionCreateUpdateDTO subscriptionCreateUpdateDTO) {
        return modelMapper.map(subscriptionCreateUpdateDTO, Subscription.class);
    }

    public SubscriptionCreateUpdateDTO toSubscriptionCreateUpdateDTO(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionCreateUpdateDTO.class);
    }
}
