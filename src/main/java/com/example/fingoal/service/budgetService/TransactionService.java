package com.example.fingoal.service.budgetService;

import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.budget.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService<S , D> {
     D createTransaction(D dto , UserBudget userBudget , Merchant merchant);

    S findTransactionById(Long id);

    D findTransactionByIdMapToDto(Long id);

    default Page<D> getAllTransactionByCategory(Long categoryId , Pageable pageable){
        return null;
    }
    default Page<D> getAllTransactionByMerchant(Long merchantId, Pageable pageable) {
        return null;
    }
    Page<D> getAllTransactionByAccount(Long accountId , Pageable pageable);

    Page<D> getAllTransactionByBudget(Long budgetId , Pageable pageable);


}
