package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.Mapper;
import com.example.fingoal.mappers.impl.BudgetMapper;
import com.example.fingoal.mappers.impl.IncomeMapper;
import com.example.fingoal.mappers.impl.OutcomeMapper;
import com.example.fingoal.mappers.impl.TransactionCategoryMapper;
import com.example.fingoal.model.*;
import com.example.fingoal.repository.UserBudgetRepository;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final UserBudgetRepository userBudgetRepository;

    private final BudgetMapper mapper;

    private final IncomeMapper incomeMapper;

    private final OutcomeMapper outcomeMapper;




    @Override
    public UserBudgetDto createBudget(UserBudgetDto userBudgetDto, User user) {
        UserBudget userBudget = mapper.mapFrom(userBudgetDto);
        userBudget.setUser(user);
        UserBudget saved = userBudgetRepository.save(userBudget);
        return mapper.mapTo(saved);
    }
    @Override
    public UserBudgetDto updateUserBudget(UserBudgetDto userBudgetDto, User user){
//        userBudget.map(
//                budget -> {
//                    Optional.ofNullable(budgetDto.budgetName).ifPresent(budget::setBudgetName);
//                }
//        )
        return null;
    }

    @Override
    public void deleteBudget(UserBudget userBudget) {
        userBudgetRepository.delete(userBudget);
    }

    @Override
    public void deleteBudget(Long budgetId) {
        userBudgetRepository.deleteById(budgetId);
    }

    @Override
    public UserBudget findUserBudgetByUser(Long userId){
        return userBudgetRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Budget with ID : %d" , userId )
                                , HttpStatus.NOT_FOUND)
                );

    }

    @Override
    public Page<TransactionDto> findAllIncomeAndOutcomeTransactions(Long budgetId , Pageable pageable) {
        Page<?> transactions = userBudgetRepository.findIncomeAndOutcomeTransactionsByUserBudgetId(budgetId , pageable);
        return transactions.map(transaction -> {
            if (transaction instanceof IncomeTransaction){
                return incomeMapper.mapTo((IncomeTransaction) transaction);
            } else if (transaction instanceof OutcomeTransaction) {
                return outcomeMapper.mapTo((OutcomeTransaction) transaction);
            }
            return null;
        });
    }

    @Override
    public UserBudgetDto findUserBudgetByUserMapToDto(Long userId) {
        return mapper.mapTo(this.findUserBudgetByUser(userId));
    }

    @Override
    public UserBudgetDto findUserBudgetByBudgetMapToDto(Long budgetId) {
        return mapper.mapTo(this.findUserBudgetByBudget(budgetId));
    }

    @Override
    public UserBudget findUserBudgetByBudget(Long budgetId){
        return userBudgetRepository.findById(budgetId).orElseThrow(RuntimeException::new);

    }
}
