package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.TransferDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.Transfer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferMapper implements Mapper<Transfer , TransferDto> {

    private final ModelMapper mapper;
    @Override
    public TransferDto mapTo(Transfer transfer) {
        this.mapper
                .typeMap(Transfer.class , TransferDto.class)
                .addMapping(mapper -> mapper.getUserBudget().getId() , TransferDto::setUserBudgetId)
                .addMapping(mapper -> mapper.getToAccount().getId() , TransferDto::setToAccount)
                .addMapping(mapper -> mapper.getFromAccount().getId() , TransferDto::setFromAccount);

        return mapper.map(transfer , TransferDto.class);
    }

    @Override
    public Transfer mapFrom(TransferDto transferDto) {
        return mapper.map(transferDto , Transfer.class);
    }
}
