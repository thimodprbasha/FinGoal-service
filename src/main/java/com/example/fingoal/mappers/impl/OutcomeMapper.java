package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.IncomeTransaction;
import com.example.fingoal.model.OutcomeTransaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutcomeMapper implements Mapper<OutcomeTransaction , OutcomeTransactionDto> {

    private final ModelMapper mapper;

    @Override
    public OutcomeTransactionDto mapTo(OutcomeTransaction outcomeTransaction) {
        this.mapper
                .typeMap(OutcomeTransaction.class , OutcomeTransactionDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId() , OutcomeTransactionDto::setUserBudgetId)
                .addMapping(mapper -> mapper.getAccount().getId(), OutcomeTransactionDto::setAccountId)
                .addMapping(mapper -> mapper.getMerchant().getId() , OutcomeTransactionDto::setMerchantId)
                .addMapping(mapper -> mapper.getCategory().getId() , OutcomeTransactionDto::setCategoryId);

        return mapper.map(outcomeTransaction , OutcomeTransactionDto.class);
    }

    @Override
    public OutcomeTransaction mapFrom(OutcomeTransactionDto outcomeTransactionDto) {
        return mapper.map(outcomeTransactionDto , OutcomeTransaction.class);
    }
}
