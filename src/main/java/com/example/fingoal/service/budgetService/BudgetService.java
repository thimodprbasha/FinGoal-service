package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.dto.CategoryDto;
import com.example.fingoal.model.UserBudget;

import java.util.List;
import java.util.Optional;

public interface BudgetService {
    BudgetDto createBudget(BudgetDto budgetDto);

    void deleteBudget(Long budgetId);

    Optional<UserBudget> userBudgetFindByUser(Long userId);

    Optional<UserBudget> userBudgetFindByBudget(Long budgetId);

    CategoryDto createTransactionCategoryByUser(CategoryDto categoryDto, Long userId);

    CategoryDto createTransactionCategoryByBudget(CategoryDto categoryDto, Long budgetId);

    List<CategoryDto> createTransactionCategoryByUser(List<CategoryDto> transactionCategories, Long userId);

    List<CategoryDto> createTransactionCategoryByBudget(List<CategoryDto> transactionCategories, Long budgetId);
}
