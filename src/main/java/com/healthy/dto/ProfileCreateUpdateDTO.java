package com.healthy.dto;

import com.healthy.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data

public class ProfileCreateUpdateDTO {
    private Integer id;
    private Float height;
    private Float weight;
    private Integer age;
    private Gender gender;
    private String healthConditions;

    @NotNull(message = "El id de usuario es obligatorio")
    private Integer userId;
}
