package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BudgetService {
    UserBudgetDto createBudget(UserBudgetDto userBudgetDto, User user);

    UserBudgetDto updateUserBudget(UserBudgetDto userBudgetDto, User user);

    void deleteBudget(UserBudget userBudget);

    void deleteBudget(Long userId);

    UserBudget findUserBudgetByUser(Long userId);

    Page<TransactionDto> findAllIncomeAndOutcomeTransactions(Long budgetId, Pageable pageable);

    UserBudget findUserBudgetByBudget(Long budgetId);

    UserBudgetDto findUserBudgetByUserMapToDto(Long userId);

    UserBudgetDto findUserBudgetByBudgetMapToDto(Long budgetId);
}
