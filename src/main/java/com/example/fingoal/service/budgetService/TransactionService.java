package com.example.fingoal.service.budgetService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService<T , D> {
    D createTransaction(D dto);

    D updateTransaction(D dto);

    T transactionFindById(Long id);

    D transactionFindByIdMapToDto(Long id);

    Page<D> getAllTransactionByCategory(Long categoryId , Pageable pageable);

    default Page<D> getAllTransactionByMerchant(Long merchantId, Pageable pageable) {
        return null;
    }

    Page<D> getAllTransactionByAccount(Long accountId , Pageable pageable);

    Page<D> getAllTransactionByBudget(Long budgetId , Pageable pageable);

    void deleteTransaction(T t);

    void deleteTransaction(Long id);

}
