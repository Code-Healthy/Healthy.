package com.healthy.mapper;

import com.healthy.dto.GoalCreateUpdateDTO;
import com.healthy.dto.GoalDetailsDTO;
import com.healthy.model.entity.Goal;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    private final ModelMapper modelMapper;

    public GoalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public GoalDetailsDTO toDetailsDTO(Goal goal) {
        GoalDetailsDTO goalDetailsDTO=modelMapper.map(goal, GoalDetailsDTO.class);

        goalDetailsDTO.setUserName(goal.getUser().getUsername());
        goalDetailsDTO.setHabitName(goal.getHabit().getName());
        goalDetailsDTO.setPlanName(goal.getPlan().getName());

        return goalDetailsDTO;

    }

    public Goal toEntity(GoalCreateUpdateDTO goalCreateUpdateDTO) {
        return modelMapper.map(goalCreateUpdateDTO, Goal.class);
    }

    public GoalCreateUpdateDTO toGoalCreateUpdateDTO(Goal goal) {
        return modelMapper.map(goal, GoalCreateUpdateDTO.class);
    }


}