package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.mappers.impl.AccountMapper;
import com.example.fingoal.model.Account;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.AccountRepository;
import com.example.fingoal.service.budgetService.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public final AccountMapper mapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto, User user) {
        Account account = accountRepository.save(mapper.mapFrom(accountDto));
        return mapper.mapTo(account);
    }

    @Override
    public AccountDto updateAccount(AccountDto account, User user) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts(User user) {
        return null;
    }

    @Override
    public Account accountFindByUser(Long id) {
        return null;
    }

    @Override
    public void deleteAccount(AccountDto account) {

    }

    @Override
    public void deleteAccount(Long id) {

    }
}
