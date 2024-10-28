package com.healthy.dto;

import com.healthy.model.enums.ERole;
import com.healthy.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class UserProfileDTO {
    private String FirstName;
    private String LastName;
    private String username;
    private String password;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;
}