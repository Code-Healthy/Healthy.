package com.healthy.dto;

import com.healthy.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProfileCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;

}