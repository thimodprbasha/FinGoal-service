package com.example.fingoal.repository;

import com.example.fingoal.model.budget.OutcomeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeTransactionRepository extends JpaRepository<OutcomeTransaction , Long> {
    Page<OutcomeTransaction> findAllByCategoryId(Long categoryId , Pageable pageable);

    Page<OutcomeTransaction> findAllByAccountId(Long accountId , Pageable pageable);

    Page<OutcomeTransaction> findAllByUserBudgetId(Long budgetId , Pageable pageable);

    Page<OutcomeTransaction> findAllByMerchantId(Long merchantId , Pageable pageable);


}
