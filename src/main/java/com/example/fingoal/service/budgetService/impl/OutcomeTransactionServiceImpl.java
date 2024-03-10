package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.model.TransactionType;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.OutcomeMapper;
import com.example.fingoal.model.*;
import com.example.fingoal.repository.OutcomeTransactionRepository;
import com.example.fingoal.service.budgetService.TransactionService;
import com.example.fingoal.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutcomeTransactionServiceImpl implements TransactionService<OutcomeTransaction , OutcomeTransactionDto> {

    private final OutcomeTransactionRepository outcomeTransactionRepository;

    private final OutcomeMapper mapper;

    @Override
    @Transactional
    public OutcomeTransactionDto createTransaction(OutcomeTransactionDto outcomeTransactionDto , UserBudget userBudget , Merchant merchant) {

        TransactionCategory transactionCategory = Utils.GetTransactionCategoryFromBudgetEntity(userBudget.getTransactionCategories().stream() , outcomeTransactionDto.getCategoryId());
        Account account = Utils.GetAccountFromBudgetEntity( userBudget.getUser().getAccounts().stream() , outcomeTransactionDto.getAccountId());

        BigDecimal deductedAccountBalance =  account.getBalance().subtract(outcomeTransactionDto.getAmount() , MathContext.DECIMAL64);

        if (deductedAccountBalance.signum() == -1){
            throw new RuntimeException();
        }

        OutcomeTransaction outcomeTransaction = mapper.mapFrom(outcomeTransactionDto);

        BigDecimal incrementUserBudgetOutcome = userBudget.getIncomeAmount().add(outcomeTransactionDto.getAmount());
        BigDecimal incrementUserBudgetCurrentAmount = userBudget.getCurrentAmount().add(outcomeTransactionDto.getAmount());
        BigDecimal currentSavingsUserBudget = userBudget.getBudgetAmount().subtract(incrementUserBudgetCurrentAmount , MathContext.DECIMAL64);
        BigDecimal incrementedCategoryCurrentAmount = transactionCategory.getCurrentAmount().add(outcomeTransactionDto.getAmount());

        account.setBalance(deductedAccountBalance);

        transactionCategory.setCurrentAmount(incrementedCategoryCurrentAmount);

        userBudget.setCurrentAmount(incrementedCategoryCurrentAmount);
        userBudget.setOutcomeAmount(incrementUserBudgetOutcome);
        userBudget.setCurrentSavings(currentSavingsUserBudget);

        outcomeTransaction.setUserBudget(userBudget);
        outcomeTransaction.setAccount(account);
        outcomeTransaction.setCategory(transactionCategory);
        outcomeTransaction.setMerchant(merchant);
        outcomeTransaction.setTransactionType(TransactionType.OUTCOME_TRANSACTION);
        outcomeTransaction.setTransactionDate(LocalDateTime.now());

        OutcomeTransaction saved = outcomeTransactionRepository.save(outcomeTransaction);

        return mapper.mapTo(saved);
    }


    @Override
    public OutcomeTransaction findTransactionById(Long outcomeTransactionId) {
        return outcomeTransactionRepository
                .findById(outcomeTransactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a OutcomeTransaction with ID : %d" , outcomeTransactionId )
                                , HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public OutcomeTransactionDto findTransactionByIdMapToDto(Long outcomeTransactionId) {
        return mapper.mapTo(this.findTransactionById(outcomeTransactionId));
    }

    @Override
    public Page<OutcomeTransactionDto> getAllTransactionByCategory(Long categoryId, Pageable pageable) {
        return outcomeTransactionRepository.findAllByCategoryId(categoryId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<OutcomeTransactionDto> getAllTransactionByMerchant(Long merchantId, Pageable pageable) {
        return outcomeTransactionRepository.findAllByMerchantId(merchantId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<OutcomeTransactionDto> getAllTransactionByAccount(Long accountId, Pageable pageable) {
        return outcomeTransactionRepository.findAllByAccountId(accountId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<OutcomeTransactionDto> getAllTransactionByBudget(Long budgetId, Pageable pageable) {
        return outcomeTransactionRepository.findAllByUserBudgetId(budgetId , pageable).map(mapper::mapTo);
    }

}
