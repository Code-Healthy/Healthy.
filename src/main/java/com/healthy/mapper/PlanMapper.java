package com.healthy.mapper;

import com.healthy.dto.PlanCreateUpdateDTO;
import com.healthy.dto.PlanDetailsDTO;
import com.healthy.model.entity.Goal;
import com.healthy.model.entity.Plan;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    private final ModelMapper modelMapper;

    public PlanMapper(ModelMapper modelMapper) {
        this.modelMapper=modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public PlanDetailsDTO toDetailsDTO(Plan plan) {
        PlanDetailsDTO dto=modelMapper.map(plan, PlanDetailsDTO.class);

        dto.setUserName(plan.getUser().getUsername());
        return dto;
    }

    public Plan toEntity(PlanCreateUpdateDTO dto) {
        return modelMapper.map(dto, Plan.class);
    }

    public PlanCreateUpdateDTO toPlanCreateUpdateDTO(Plan plan) {
        return modelMapper.map(plan, PlanCreateUpdateDTO.class);
    }
}