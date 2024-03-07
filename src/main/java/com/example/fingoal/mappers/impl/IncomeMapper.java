package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.IncomeTransaction;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class IncomeMapper implements Mapper<IncomeTransaction , IncomeTransactionDto> {

    private final ModelMapper mapper;
    @Override
    public IncomeTransactionDto mapTo(IncomeTransaction incomeTransaction) {
        this.mapper
                .typeMap(IncomeTransaction.class , IncomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId() , IncomeTransactionDto::setUserBudgetId);
        return mapper.map(incomeTransaction , IncomeTransactionDto.class) ;
    }

    @Override
    public IncomeTransaction mapFrom(IncomeTransactionDto incomeTransactionDto) {
        return mapper.map(incomeTransactionDto , IncomeTransaction.class);
    }
}
