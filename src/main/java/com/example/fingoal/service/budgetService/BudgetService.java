package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.RequestUserBudgetDto;
import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.ResponseUserBudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BudgetService {
    ResponseUserBudgetDto createBudget(ResponseUserBudgetDto responseUserBudgetDto, User user);

    ResponseUserBudgetDto updateUserBudget(Long budgetId , RequestUserBudgetDto requestUserBudgetDto);

    void deleteBudget(UserBudget userBudget);

    void deleteBudget(Long userId);

    UserBudget findUserBudgetByUser(Long userId);

    Page<TransactionDto> findAllIncomeAndOutcomeTransactions(Long budgetId, Pageable pageable);

    UserBudget findUserBudgetByBudget(Long budgetId);

    ResponseUserBudgetDto findUserBudgetByUserMapToDto(Long userId);

    ResponseUserBudgetDto findUserBudgetByBudgetMapToDto(Long budgetId);

    boolean isUserBudgetExistsOnUser(UserBudget userBudget);

    void isUserBudgetExistsOnConnectedUser(UserBudget userBudget);

}
