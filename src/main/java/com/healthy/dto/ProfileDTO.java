package com.healthy.dto;

import com.healthy.model.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;

    private List<PlanDTO> plans;
    private List<ProfileResourceDetailsDTO> resources;
    private List<ProfileSubscriptionDTO> subPlans;

}