package com.example.fingoal.service.budgetService;

import com.example.fingoal.model.Merchant;
import com.example.fingoal.model.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TransactionService<S , D> {
    D createTransaction(D dto , UserBudget userBudget , Merchant merchant);

    S findTransactionById(Long id);

    D findTransactionByIdMapToDto(Long id);

    Page<D> getAllTransactionByCategory(Long categoryId , Pageable pageable);

    default Page<D> getAllTransactionByMerchant(Long merchantId, Pageable pageable) {
        return null;
    }

    Page<D> getAllTransactionByAccount(Long accountId , Pageable pageable);

    Page<D> getAllTransactionByBudget(Long budgetId , Pageable pageable);


}
