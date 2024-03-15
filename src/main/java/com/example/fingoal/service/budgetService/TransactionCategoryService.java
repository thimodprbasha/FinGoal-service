package com.example.fingoal.service.budgetService;

import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.model.budget.TransactionCategory;
import com.example.fingoal.model.budget.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionCategoryService {
    TransactionCategoryDto createTransactionCategory(TransactionCategoryDto transactionCategoryDto, UserBudget userBudget);

    Page<TransactionCategoryDto> findAllCategoryByBudget(Long budgetId , Pageable pageable);

    TransactionCategory findByCategoryName(Long budgetId , String categoryName);

    TransactionCategoryDto findByCategoryNameMapToDto(Long budgetId , String categoryName);

    TransactionCategoryDto findByCategoryIdMapToDto(Long categoryId);

    TransactionCategory findByCategoryId(Long categoryId);

    TransactionCategory updateCategory(TransactionCategoryDto transactionCategoryDto, UserBudget userBudget);

    void  deleteCategory(Long categoryId);
    void  deleteCategory(TransactionCategory transactionCategory);

}
