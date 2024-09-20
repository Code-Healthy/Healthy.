package com.healthy.dto;

import com.healthy.model.enums.GoalStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoalDetailsDTO {

    private Integer id;
    private Float targetValue;
    private Float currentValue;
    private LocalDateTime endDate;
    private GoalStatus goalStatus;
    private String userName;
    private String habitName;
    private String planName;
    private List<TrackingRecordDetailsDTO> items;
}
