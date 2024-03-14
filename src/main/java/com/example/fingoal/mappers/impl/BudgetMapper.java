package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.dto.ResponseUserBudgetDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.budget.IncomeTransaction;
import com.example.fingoal.model.budget.OutcomeTransaction;
import com.example.fingoal.model.budget.TransactionCategory;
import com.example.fingoal.model.budget.UserBudget;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BudgetMapper implements Mapper<UserBudget, ResponseUserBudgetDto> {

    private final ModelMapper mapper;

    @Override
    public ResponseUserBudgetDto mapTo(UserBudget userBudget) {
        this.mapper
                .typeMap(TransactionCategory.class, TransactionCategoryDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), TransactionCategoryDto::setUserBudgetId);
        this.mapper
                .typeMap(IncomeTransaction.class, IncomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), IncomeTransactionDto::setUserBudgetId);

        this.mapper
                .typeMap(OutcomeTransaction.class, OutcomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), OutcomeTransactionDto::setUserBudgetId);

        this.mapper.typeMap(UserBudget.class, ResponseUserBudgetDto.class)
                .addMapping(mapper -> mapper.getUser().getId(), ResponseUserBudgetDto::setUserId);

        return mapper.map(userBudget, ResponseUserBudgetDto.class);

    }

    @Override
    public UserBudget mapFrom(ResponseUserBudgetDto responseUserBudgetDto) {
        return mapper.map(responseUserBudgetDto, UserBudget.class);
    }
}
