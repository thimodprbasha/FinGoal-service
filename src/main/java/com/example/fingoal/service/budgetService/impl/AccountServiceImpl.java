package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.AccountMapper;
import com.example.fingoal.model.Account;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.AccountRepository;
import com.example.fingoal.service.budgetService.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public final AccountMapper mapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto, User user) {
        Account account = mapper.mapFrom(accountDto);
        account.setUser(user);
        Account saved = accountRepository.save(account);
        return mapper.mapTo(saved);
    }

    @Override
    public AccountDto updateAccount(AccountDto account, User user) {
        return null;
    }

    @Override
    public Page<AccountDto> getAllAccountsByUser(Long userId , Pageable pageable) {
        return accountRepository.findAllByUserId(userId , pageable).map(mapper::mapTo);
    }

    @Override
    public Account findAccountByUser(Long userId) {
        return accountRepository.findByUserId(userId).orElseThrow();
    }

    @Override
    public Account findAccountById(Long accountID) {
        return accountRepository
                .findById(accountID)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Account with ID : %d" , accountID )
                                , HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public AccountDto findAccountByIdMapToDto(Long accountID) {
        return mapper.mapTo(this.findAccountById(accountID));
    }

    @Override
    public AccountDto findAccountByUserMapToDto(Long userId) {
        return mapper.mapTo(this.findAccountByUser(userId));
    }

    @Override
    public AccountDto findAccountByNumber(String accountNumber) {
        return mapper.mapTo(accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Account with Number : \"%s\"" , accountNumber )
                                , HttpStatus.NOT_FOUND)
                ));
    }

    @Override
    public void deleteAccount(Account account) {
        this.accountRepository.delete(account);

    }

    @Override
    public void deleteAccount(Long accountId) {
        this.accountRepository.deleteById(accountId);
    }
}
