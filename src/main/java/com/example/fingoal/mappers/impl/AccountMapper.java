package com.example.fingoal.mappers.impl;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper implements Mapper<Account , AccountDto> {

    private final ModelMapper modelMapper;

    @Override
    public AccountDto mapTo(Account account) {
       return modelMapper.map(account , AccountDto.class);
    }

    @Override
    public Account mapFrom(AccountDto accountDto) {
        return modelMapper.map(accountDto , Account.class);
    }
}
