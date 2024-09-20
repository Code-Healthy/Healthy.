package com.healthy.dto;

import com.healthy.model.entity.Goal;
import com.healthy.model.enums.PlanStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PlanCreateUpdateDTO {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime endDate;
    private PlanStatus planStatus;
    private List<Goal> goals;
    private Integer userId;
}
