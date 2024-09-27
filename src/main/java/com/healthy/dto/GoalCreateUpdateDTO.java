package com.healthy.dto;


import com.healthy.model.entity.TrackingRecord;
import com.healthy.model.enums.GoalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalCreateUpdateDTO {
    private Integer id;

    //@NotBlank(message = "El valor objetivo es obligatorio")
    @Positive(message = "El valor debe ser positivo")
    private Float targetValue;

    //@NotBlank(message = "El valor actual es obligatorio")
    @Positive(message = "El valor debe ser positivo")
    private Float currentValue;

    //@NotBlank(message = "La fecha de término es obligatoria")
    private LocalDateTime endDate;

    //@NotBlank(message = "El estado de la meta es obligatorio")
    private GoalStatus goalStatus;

    private List<TrackingRecordCreateUpdateDTO> items;

    //@NotNull(message = "El usuario es obligatorio")
    private Integer userId;

    //@NotNull(message = "El plan es obligatorio")
    private Integer planId;

    //@NotNull(message = "El hábito es obligatorio")
    private Integer habitId;
}