package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.model.customer.Account;
import com.example.fingoal.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountDto createAccount(AccountDto account , User user);

    AccountDto updateAccount(AccountDto account , User user);

    Page<AccountDto> getAllAccountsByUser(Long userId , Pageable pageable);

    Account findAccountByUser(Long userId);

    Account findAccountById(Long accountId);

    AccountDto findAccountByIdMapToDto(Long accountId);

    AccountDto findAccountByUserMapToDto(Long userId);

    AccountDto findAccountByNumber(String accountNumber);

    void deleteAccount(Account account);

    void deleteAccount(Long id);
}
