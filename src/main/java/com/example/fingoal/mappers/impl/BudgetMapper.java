package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.UserBudget;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetMapper implements Mapper<UserBudget , BudgetDto> {

    private final ModelMapper mapper;
    @Override
    public BudgetDto mapTo(UserBudget userBudget) {
        return mapper.map(userBudget , BudgetDto.class);
    }

    @Override
    public UserBudget mapFrom(BudgetDto budgetDto) {
        return mapper.map(budgetDto , UserBudget.class);
    }
}
