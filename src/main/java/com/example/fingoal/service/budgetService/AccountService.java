package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.model.Account;
import com.example.fingoal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account , User user);

    AccountDto updateAccount(AccountDto account , User user);

    Page<AccountDto> getAllAccountsByUser(Long userId , Pageable pageable);

    Account accountFindByUser(Long userId);

    //FIXME change find by names to proper names

    Account accountFindById(Long accountId);

    AccountDto accountFindByIdMapToDto(Long accountId);

    AccountDto accountFindByUserMapToDto(Long userId);

    AccountDto accountFindByNumber(String accountNumber);

    void deleteAccount(Account account);

    void deleteAccount(Long id);
}
