package com.healthy.dto;

import com.healthy.model.entity.Goal;
import com.healthy.model.entity.User;
import com.healthy.model.enums.Frequency;
import com.healthy.model.enums.GoalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackingRecordDetailsDTO {
    //DATOS DE LA MISMA CLASE
    private Integer id;
    private Float value;
    private LocalDateTime date;
    private String note;

    //DATOS DE GOALS
    private Float targetValue;
    private Float currentValue;
    private GoalStatus goalStatus;

    //DATOS DE HABIT
    private String habitName;
    private String habitDescription;
    private Frequency habitFrequency;

    //DATOS HABIT TYPE
    private String habitTypeName;
    private String habitTypeDescription;

}