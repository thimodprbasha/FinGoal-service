package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;

@Component
@RequiredArgsConstructor
public class AccountMapper implements Mapper<Account , AccountDto> {

    private final ModelMapper modelMapper;

    @Override
    public AccountDto mapTo(Account account) {
        this.modelMapper
                .typeMap(Account.class , AccountDto.class)
                .addMapping(mapper -> mapper.getUser().getId() , AccountDto::setUserId);

        return modelMapper.map(account , AccountDto.class);
    }

    @Override
    public Account mapFrom(AccountDto accountDto) {
        return modelMapper.map(accountDto , Account.class);
    }
}
