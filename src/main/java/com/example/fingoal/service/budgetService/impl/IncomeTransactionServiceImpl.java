package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.mappers.impl.IncomeMapper;
import com.example.fingoal.model.IncomeTransaction;
import com.example.fingoal.repository.IncomeTransactionRepository;
import com.example.fingoal.service.budgetService.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeTransactionServiceImpl implements TransactionService<IncomeTransaction , IncomeTransactionDto> {

    private final IncomeTransactionRepository incomeTransactionRepository;

    private final IncomeMapper mapper;

    @Override
    public IncomeTransactionDto createTransaction(IncomeTransactionDto transactionDto) {
        IncomeTransaction incomeTransaction = mapper.mapFrom(transactionDto);
        IncomeTransaction saved = incomeTransactionRepository.save(incomeTransaction);
        return mapper.mapTo(saved);
    }

    @Override
    public IncomeTransactionDto updateTransaction(IncomeTransactionDto transactionDto) {
        return null;
    }

    @Override
    public IncomeTransaction transactionFindById(Long incomeTransactionId) {
        return incomeTransactionRepository.findById(incomeTransactionId).orElseThrow(RuntimeException::new);
    }

    @Override
    public IncomeTransactionDto transactionFindByIdMapToDto(Long incomeTransactionId) {
        return mapper.mapTo(this.transactionFindById(incomeTransactionId));
    }

    @Override
    public Page<IncomeTransactionDto> getAllTransactionByCategory(Long categoryId, Pageable pageable) {
        return incomeTransactionRepository.findAllByCategoryId(categoryId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<IncomeTransactionDto> getAllTransactionByAccount(Long accountId, Pageable pageable) {
        return incomeTransactionRepository.findAllByAccountId(accountId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<IncomeTransactionDto> getAllTransactionByBudget(Long budgetId, Pageable pageable) {
        return incomeTransactionRepository.findAllByUserBudgetId(budgetId , pageable).map(mapper::mapTo);
    }

    @Override
    public void deleteTransaction(IncomeTransaction incomeTransaction) {
        incomeTransactionRepository.delete(incomeTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        incomeTransactionRepository.deleteById(id);
    }
}
