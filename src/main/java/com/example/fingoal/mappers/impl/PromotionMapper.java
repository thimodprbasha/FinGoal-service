package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.PromotionDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.merchant.Promotion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PromotionMapper implements Mapper<Promotion , PromotionDto> {

    private final ModelMapper mapper;
    @Override
    public PromotionDto mapTo(Promotion promotion) {
//        this.mapper
//                .typeMap(Promotion.class , PromotionDto.class)
//                .
        return mapper.map(promotion , PromotionDto.class);
    }

    @Override
    public Promotion mapFrom(PromotionDto promotionDto) {
        return mapper.map(promotionDto , Promotion.class);
    }
}
