package com.healthy.dto;

import com.healthy.model.entity.Goal;
import com.healthy.model.enums.PlanStatus;
import lombok.Data;
import java.util.List;

@Data
public class PlanDetailsDTO {
    private Integer id;
    private String name;
    private String userName;
    private String description;
    private PlanStatus planStatus;
    private List<Goal> goals;
}
