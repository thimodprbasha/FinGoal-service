package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;

public interface BudgetService {
    UserBudgetDto createBudget(UserBudgetDto userBudgetDto, User user);

    UserBudgetDto updateUserBudget(UserBudgetDto userBudgetDto, User user);

    void deleteBudget(UserBudget userBudget);

    void deleteBudget(Long userId);

    UserBudget findUserBudgetByUser(Long userId);

    UserBudget findUserBudgetByBudget(Long budgetId);

    UserBudgetDto findUserBudgetByUserMapToDto(Long userId);

    UserBudgetDto findUserBudgetByBudgetMapToDto(Long budgetId);
}
