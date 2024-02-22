package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.UserBudget;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetMapper implements Mapper<UserBudget , UserBudgetDto> {

    private final ModelMapper mapper;
    @Override
    public UserBudgetDto mapTo(UserBudget userBudget) {
        return mapper.map(userBudget , UserBudgetDto.class);
    }

    @Override
    public UserBudget mapFrom(UserBudgetDto userBudgetDto) {
        return mapper.map(userBudgetDto, UserBudget.class);
    }
}
