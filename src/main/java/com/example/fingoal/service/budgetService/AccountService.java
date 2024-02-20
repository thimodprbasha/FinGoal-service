package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.model.Account;
import com.example.fingoal.model.User;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account , User user);

    AccountDto updateAccount(AccountDto account , User user);

    List<AccountDto> getAllAccounts(User user);

    Account accountFindByUser(Long id);

    void deleteAccount(AccountDto account);

    void deleteAccount(Long id);
}
