package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.mappers.impl.AccountMapper;
import com.example.fingoal.model.Account;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.AccountRepository;
import com.example.fingoal.service.budgetService.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public final AccountMapper mapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto, User user) {
        Account account = mapper.mapFrom(accountDto);
        Account saved = accountRepository.save(account);
        return mapper.mapTo(saved);
    }

    @Override
    public AccountDto updateAccount(AccountDto account, User user) {
        return null;
    }

    @Override
    public Page<AccountDto> getAllAccountsByUser(Long userId , Pageable pageable) {
        return accountRepository.findAllByUserId(userId , pageable).map(
                account -> mapper.mapTo(account)
        );
    }


    @Override
    public Account accountFindByUser(Long userId) {
        return accountRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Account accountFindById(Long accountID) {
        return accountRepository.findById(accountID).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public AccountDto accountFindByIdMapToDto(Long accountID) {
        return mapper.mapTo(this.accountFindById(accountID));
    }

    @Override
    public AccountDto accountFindByUserMapToDto(Long userId) {
        return mapper.mapTo(this.accountFindByUser(userId));
    }

    @Override
    public AccountDto accountFindByNumber(String accountNumber) {
        return mapper.mapTo(accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException()));
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
