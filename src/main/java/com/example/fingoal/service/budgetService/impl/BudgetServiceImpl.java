package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.repository.UserBudgetRepository;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import com.example.fingoal.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final UserService userService;

    private final UserBudgetRepository userBudgetRepository;

    private final TransactionCategoryService transactionCategoryService;

    @Override
    public BudgetDto createBudget(BudgetDto budgetDto , User user) {
//        var user = userService.isUserExist(budgetDto.userId);
        UserBudget userBudget = UserBudget.builder()
                .user(user)
                .budgetName(budgetDto.getBudgetName())
                .budgetAmount(new BigDecimal(200000))
                .totalAmount(new BigDecimal(0))
                .currentSavings(new BigDecimal(0))
                .previousAmount(new BigDecimal(0))
                .incomeAmount(new BigDecimal(0))
                .outcomeAmount(new BigDecimal(0))
                .startDate(LocalDate.now())
                .build();

        var budget = userBudgetRepository.save(userBudget);

        if (!budgetDto.categories.isEmpty()){
            transactionCategoryService.createTransactionCategory(budgetDto.categories , budget);
        }

        return budgetDto;
    }
    @Override
    public BudgetDto updateUserBudget(BudgetDto budgetDto, User user){
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
    public UserBudget userBudgetFindByUser(Long userId){
        return userBudgetRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException());

    }
    @Override
    public UserBudget userBudgetFindByBudget(Long budgetId){
        return userBudgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException());

    }
}
