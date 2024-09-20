package com.healthy.dto;

import com.healthy.model.enums.PaymentStatus;
import com.healthy.model.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public class SubscriptionDTO {

    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PaymentStatus paymentStatus;
    private SubscriptionStatus subscriptionStatus;

    //Datos usuarios
    private String userName;

    //Datos resource
}
