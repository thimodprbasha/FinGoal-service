package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.mappers.impl.OutcomeMapper;
import com.example.fingoal.model.OutcomeTransaction;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.repository.OutcomeTransactionRepository;
import com.example.fingoal.service.budgetService.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutcomeTransactionServiceImpl implements TransactionService<OutcomeTransaction , OutcomeTransactionDto> {

    private final OutcomeTransactionRepository outcomeTransactionRepository;

    private final OutcomeMapper mapper;

    @Override
    public OutcomeTransactionDto createTransaction(OutcomeTransactionDto outcomeTransactionDto , UserBudget userBudget) {
        OutcomeTransaction outcomeTransaction = mapper.mapFrom(outcomeTransactionDto);
        OutcomeTransaction saved = outcomeTransactionRepository.save(outcomeTransaction);
        return mapper.mapTo(saved);
    }


    @Override
    public OutcomeTransaction findTransactionById(Long outcomeTransactionId) {
        return outcomeTransactionRepository.findById(outcomeTransactionId).orElseThrow(RuntimeException::new);
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
