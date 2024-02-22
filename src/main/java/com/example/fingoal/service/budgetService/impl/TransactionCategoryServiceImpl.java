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

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    private final TransactionCategoryRepository transactionCategoryRepository;

    private final TransactionCategoryMapper mapper;

    @Override
    public TransactionCategoryDto createTransactionCategory(TransactionCategoryDto transactionCategoryDto, UserBudget userBudget){
        TransactionCategory transactionCategory = TransactionCategory.builder()
                .userBudget(userBudget)
                .categoryName(transactionCategoryDto.getCategoryName())
                .setAmount(transactionCategoryDto.getSetAmount())
                .icon(transactionCategoryDto.getIcon())
                .build();

        TransactionCategory saved =  transactionCategoryRepository.save(transactionCategory);
        return mapper.mapTo(saved);
    }
    @Override
    public List<TransactionCategoryDto> createTransactionCategory(List<TransactionCategoryDto> transactionCategories , UserBudget userBudget){
        List<TransactionCategory> transactionCategoryList =  transactionCategories.stream().map(
                        categoryDto ->
                                TransactionCategory.builder()
                                        .userBudget(userBudget)
                                        .categoryName(categoryDto.getCategoryName())
                                        .setAmount(categoryDto.getSetAmount())
                                        .icon(categoryDto.getIcon())
                                        .build())
                .collect(Collectors.toList());

        List<TransactionCategory> savedAll = transactionCategoryRepository.saveAll(transactionCategoryList);
        return savedAll.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @Override
    public Page<TransactionCategoryDto> findAllCategoryByBudget(Long budgetId , Pageable pageable) {
        return transactionCategoryRepository.findAllByUserBudgetId(budgetId , pageable).map(mapper::mapTo);
    }

    @Override
    public TransactionCategory findByCategoryName(String categoryName) {
        return transactionCategoryRepository.findByCategoryName(categoryName).orElseThrow(RuntimeException::new);
    }

    @Override
    public TransactionCategoryDto findByCategoryNameMapToDto(String categoryName) {
        return mapper.mapTo(this.findByCategoryName(categoryName));
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
