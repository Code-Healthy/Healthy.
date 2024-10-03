package com.healthy.dto;

import com.healthy.model.enums.Gender;
import lombok.Data;
@Data

public class ProfileDetailsDTO {
    private Integer id;
    private String userName;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;
}