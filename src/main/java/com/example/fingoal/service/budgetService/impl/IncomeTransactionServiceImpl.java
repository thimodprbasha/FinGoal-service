package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.model.TransactionType;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.IncomeMapper;
import com.example.fingoal.model.*;
import com.example.fingoal.repository.IncomeTransactionRepository;
import com.example.fingoal.service.budgetService.TransactionService;
import com.example.fingoal.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IncomeTransactionServiceImpl implements TransactionService<IncomeTransaction , IncomeTransactionDto> {

    private final IncomeTransactionRepository incomeTransactionRepository;

    private final IncomeMapper mapper;

    @Override
    @Transactional
    public IncomeTransactionDto createTransaction(IncomeTransactionDto transactionDto , UserBudget userBudget , Merchant merchant) {

        Account account = Utils.GetAccountFromBudgetEntity( userBudget.getUser().getAccounts().stream() , transactionDto.getAccountId());

        BigDecimal incrementedAccountBalance = account.getBalance().add(transactionDto.getAmount());
        BigDecimal incrementedUserBudgetIncome = userBudget.getIncomeAmount().add(transactionDto.getAmount());

        account.setBalance(incrementedAccountBalance);
        userBudget.setIncomeAmount(incrementedUserBudgetIncome);

        IncomeTransaction incomeTransaction = mapper.mapFrom(transactionDto);
        incomeTransaction.setAccount(account);
        incomeTransaction.setUserBudget(userBudget);
        incomeTransaction.setTransactionType(TransactionType.INCOME_TRANSACTION);
        incomeTransaction.setTransactionDate(LocalDateTime.now());

        IncomeTransaction saved = incomeTransactionRepository.save(incomeTransaction);
        return mapper.mapTo(saved);
    }



    @Override
    public IncomeTransaction findTransactionById(Long incomeTransactionId) {
        return incomeTransactionRepository
                .findById(incomeTransactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a IncomeTransaction with ID : %d" , incomeTransactionId )
                                , HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public IncomeTransactionDto findTransactionByIdMapToDto(Long incomeTransactionId) {
        return mapper.mapTo(this.findTransactionById(incomeTransactionId));
    }

    @Override
    public Page<IncomeTransactionDto> getAllTransactionByAccount(Long accountId, Pageable pageable) {
        return incomeTransactionRepository.findAllByAccountId(accountId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<IncomeTransactionDto> getAllTransactionByBudget(Long budgetId, Pageable pageable) {
        return incomeTransactionRepository.findAllByUserBudgetId(budgetId , pageable).map(mapper::mapTo);
    }

}
