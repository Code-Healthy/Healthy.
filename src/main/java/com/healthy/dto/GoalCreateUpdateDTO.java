package com.healthy.dto;

import com.healthy.model.enums.GoalStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalCreateUpdateDTO {
    private Integer id;

    @NotNull(message = "El valor objetivo es obligatorio")
    @Positive(message = "El valor debe ser positivo")
    private Float targetValue;

    @NotNull(message = "El valor actual es obligatorio")
    @Positive(message = "El valor debe ser positivo")
    private Float currentValue;

    @NotNull(message = "La fecha de término es obligatoria")
    private LocalDateTime endDate;

    @NotNull(message = "El estado de la meta es obligatoria")
    private GoalStatus goalStatus;

    private List<TrackingRecordCreateUpdateDTO> items;

    @NotNull(message = "El perfil es obligatorio")
    private Integer profileId;

    @NotNull(message = "El plan es obligatorio")
    private Integer planId;

    @NotNull(message = "El hábito es obligatorio")
    private Integer habitId;
}