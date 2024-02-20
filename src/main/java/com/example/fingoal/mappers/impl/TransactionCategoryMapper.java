package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.TransactionCategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionCategoryMapper implements Mapper<TransactionCategory , TransactionCategoryDto> {

    private final ModelMapper mapper;

    @Override
    public TransactionCategoryDto mapTo(TransactionCategory transactionCategory) {
        return mapper.map(transactionCategory , TransactionCategoryDto.class) ;
    }

    @Override
    public TransactionCategory mapFrom(TransactionCategoryDto transactionCategoryDto) {
        return mapper.map(transactionCategoryDto , TransactionCategory.class);
    }
}
