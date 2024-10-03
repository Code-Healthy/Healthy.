package com.healthy.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileResourceDetailsDTO {
    private boolean is_active;
    private LocalDateTime access_expiration;

    private String resourceTittle;
    private String resourceDescription;

    private String expertFirstName;
    private String expertLastName;
    private String expertExpertise;

    private String subPlanName;

    private String userName;
}
