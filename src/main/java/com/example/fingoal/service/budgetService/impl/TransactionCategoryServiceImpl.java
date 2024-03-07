package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.TransactionCategoryDto;
import com.example.fingoal.mappers.impl.TransactionCategoryMapper;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.repository.TransactionCategoryRepository;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionCategoryMapper mapper;

    @Override
    @Transactional
    public TransactionCategoryDto createTransactionCategory(TransactionCategoryDto transactionCategoryDto, UserBudget userBudget) {
        if (!userBudget.isCategoryFull()) {

            BigDecimal incrementedCategoryAmount = userBudget.getCategoryTotalAmount().add(transactionCategoryDto.getSetAmount());
            int compareCategoryAmount = userBudget.getBudgetAmount().compareTo(incrementedCategoryAmount);

            if (compareCategoryAmount == 0) {
                userBudget.setCategoryFull(true);
            } else if (compareCategoryAmount < 0) {
                throw new RuntimeException();
            }

            TransactionCategory transactionCategory = mapper.mapFrom(transactionCategoryDto);
            userBudget.setCategoryTotalAmount(incrementedCategoryAmount);
            transactionCategory.setUserBudget(userBudget);

            TransactionCategory saved = transactionCategoryRepository.save(transactionCategory);
            return mapper.mapTo(saved);
        } else
            throw new RuntimeException();
    }


    @Override
    public Page<TransactionCategoryDto> findAllCategoryByBudget(Long budgetId, Pageable pageable) {
        return transactionCategoryRepository.findAllByUserBudgetId(budgetId, pageable).map(mapper::mapTo);
    }

    @Override
    public TransactionCategory findByCategoryName(Long budgetId, String categoryName) {
        return transactionCategoryRepository.findByUserBudgetIdAndCategoryName(budgetId, categoryName).orElseThrow(RuntimeException::new);
    }

    @Override
    public TransactionCategoryDto findByCategoryNameMapToDto(Long budgetId, String categoryName) {
        return mapper.mapTo(this.findByCategoryName(budgetId, categoryName));
    }

    @Override
    public TransactionCategoryDto findByCategoryIdMapToDto(Long categoryId) {
        return mapper.mapTo(this.findByCategoryId(categoryId));
    }

    @Override
    public TransactionCategory findByCategoryId(Long categoryId) {
        return transactionCategoryRepository.findById(categoryId).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public TransactionCategory updateCategory(TransactionCategoryDto transactionCategoryDto, UserBudget userBudget) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        transactionCategoryRepository.deleteById(categoryId);
    }

    @Override
    public void deleteCategory(TransactionCategory transactionCategory) {
        transactionCategoryRepository.delete(transactionCategory);
    }
}
