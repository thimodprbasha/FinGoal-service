package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.IncomeTransaction;
import com.example.fingoal.model.OutcomeTransaction;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BudgetMapper implements Mapper<UserBudget, UserBudgetDto> {

    private final ModelMapper mapper;

    @Override
    public UserBudgetDto mapTo(UserBudget userBudget) {
        this.mapper
                .typeMap(TransactionCategory.class, TransactionCategoryDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), TransactionCategoryDto::setUserBudgetId);
        this.mapper
                .typeMap(IncomeTransaction.class, IncomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), IncomeTransactionDto::setUserBudgetId);

        this.mapper
                .typeMap(OutcomeTransaction.class, OutcomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId(), OutcomeTransactionDto::setUserBudgetId);

        this.mapper.typeMap(UserBudget.class, UserBudgetDto.class)
                .addMapping(mapper -> mapper.getUser().getId(), UserBudgetDto::setUserId);

        return mapper.map(userBudget, UserBudgetDto.class);

    }

    @Override
    public UserBudget mapFrom(UserBudgetDto userBudgetDto) {
        return mapper.map(userBudgetDto, UserBudget.class);
    }
}
