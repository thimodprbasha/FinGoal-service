package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.dto.CategoryDto;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.repository.TransactionCategoryRepository;
import com.example.fingoal.repository.UserBudgetRepository;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final UserService userService;

    private final UserBudgetRepository userBudgetRepository;

    private final TransactionCategoryRepository transactionCategoryRepository;

    @Override
    public BudgetDto createBudget(BudgetDto budgetDto) {
        var user = userService.isUserExist(budgetDto.userId).get();
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
           createTransactionCategoryByBudget(budgetDto.categories , budget.getId());
        }

        return budgetDto;
    }

    @Override
    public void deleteBudget(Long budgetId) {
        userBudgetRepository.deleteById(budgetId);
    }

    @Override
    public Optional<UserBudget> userBudgetFindByUser(Long userId){
        var userBudget = userBudgetRepository.findByUserId(userId);
        if (userBudget.isEmpty()){
            throw new RuntimeException();
        }
        return userBudget;

    }
    @Override
    public Optional<UserBudget> userBudgetFindByBudget(Long budgetId){
        var userBudget = userBudgetRepository.findById(budgetId);
        if (userBudget.isEmpty()){
            throw new RuntimeException();
        }
        return userBudget;

    }
    @Override
    public CategoryDto createTransactionCategoryByUser(CategoryDto categoryDto, Long userId){
        UserBudget userBudget = userBudgetFindByUser(userId).get();
        return createTransactionCategory(categoryDto , userBudget);
    }
    @Override
    public CategoryDto createTransactionCategoryByBudget(CategoryDto categoryDto, Long budgetId){
        UserBudget userBudget = userBudgetFindByBudget(budgetId).get();
        return createTransactionCategory(categoryDto , userBudget);
    }
    @Override
    public List<CategoryDto> createTransactionCategoryByUser(List<CategoryDto> transactionCategories, Long userId){
        UserBudget userBudget = userBudgetFindByUser(userId).get();
        return createTransactionCategory(transactionCategories , userBudget);
    }
    @Override
    public List<CategoryDto> createTransactionCategoryByBudget(List<CategoryDto> transactionCategories , Long budgetId){
        UserBudget userBudget = userBudgetFindByBudget(budgetId).get();
        return createTransactionCategory(transactionCategories , userBudget);
    }

    private CategoryDto createTransactionCategory(CategoryDto categoryDto ,UserBudget userBudget) {
        TransactionCategory transactionCategory = TransactionCategory.builder()
                .userBudget(userBudget)
                .categoryName(categoryDto.getCategoryName())
                .setAmount(categoryDto.getSetAmount())
                .icon(categoryDto.getIcon())
                .build();
       var id =  transactionCategoryRepository.save(transactionCategory);

        return categoryDto;
    }

    private List<CategoryDto> createTransactionCategory(List<CategoryDto> transactionCategory , UserBudget userBudget) {
        List<TransactionCategory> transactionCategoryList =  transactionCategory.stream().map(
                        categoryDto ->
                                TransactionCategory.builder()
                                        .userBudget(userBudget)
                                        .categoryName(categoryDto.getCategoryName())
                                        .setAmount(categoryDto.getSetAmount())
                                        .icon(categoryDto.getIcon())
                                        .build())
                .collect(Collectors.toList());

        transactionCategoryRepository.saveAll(transactionCategoryList);

        return transactionCategory;
    }
}
