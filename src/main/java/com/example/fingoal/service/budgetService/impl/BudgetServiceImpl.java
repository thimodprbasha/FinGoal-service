package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.mappers.impl.BudgetMapper;
import com.example.fingoal.model.User;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.repository.UserBudgetRepository;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final UserBudgetRepository userBudgetRepository;

    private final TransactionCategoryService transactionCategoryService;

    private final BudgetMapper mapper;

    @Override
    public UserBudgetDto createBudget(UserBudgetDto userBudgetDto, User user) {
//        var user = userService.isUserExist(budgetDto.userId);
        UserBudget userBudget = UserBudget.builder()
                .user(user)
                .budgetName(userBudgetDto.getBudgetName())
                .budgetAmount(new BigDecimal(200000))
                .totalAmount(new BigDecimal(0))
                .currentSavings(new BigDecimal(0))
                .previousAmount(new BigDecimal(0))
                .incomeAmount(new BigDecimal(0))
                .outcomeAmount(new BigDecimal(0))
                .startDate(LocalDate.now())
                .build();

        var budget = userBudgetRepository.save(userBudget);

        if (!userBudgetDto.categories.isEmpty()){
            transactionCategoryService.createTransactionCategory(userBudgetDto.categories , budget);
        }

        return userBudgetDto;
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
        return userBudgetRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException());

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
        return userBudgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException());

    }
}
