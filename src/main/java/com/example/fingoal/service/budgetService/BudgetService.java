package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;

public interface BudgetService {
    BudgetDto createBudget(BudgetDto budgetDto , User user);

    BudgetDto updateUserBudget(BudgetDto budgetDto, User user);

    void deleteBudget(UserBudget userBudget);

    void deleteBudget(Long userId);

    UserBudget userBudgetFindByUser(Long userId);

    UserBudget userBudgetFindByBudget(Long budgetId);
}
