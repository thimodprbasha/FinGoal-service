package com.example.fingoal.service.budgetService;

public interface BudgetInterceptor {

    boolean deductFromAccount();

    boolean updateAccount();

    boolean updateTransactionCategory();

    boolean updateBudget();
}
