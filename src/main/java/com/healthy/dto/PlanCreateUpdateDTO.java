package com.healthy.dto;

import com.healthy.model.entity.Goal;
import com.healthy.model.enums.PlanStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class PlanCreateUpdateDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "La descripción no puede estar en blanco")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String description;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDateTime endDate;

    @NotNull(message = "El estado del plan no puede ser nulo")
    private PlanStatus planStatus;

    @NotNull(message = "El userId no puede ser nulo")
    private Integer profileId;

    private List<Goal> goals;
}