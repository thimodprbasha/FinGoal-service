package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.merchant.Merchant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MerchantMapper implements Mapper<Merchant, MerchantDto> {

    private final ModelMapper modelMapper;
    @Override
    public MerchantDto mapTo(Merchant merchant) {
//        this.modelMapper
//                .typeMap(MerchantDto.class , Merchant.class)
//                .addMapping()
        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public Merchant mapFrom(MerchantDto merchantDto) {
        return modelMapper.map(merchantDto , Merchant.class);
    }
}
