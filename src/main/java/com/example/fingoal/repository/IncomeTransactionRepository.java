package com.example.fingoal.repository;

import com.example.fingoal.model.IncomeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction , Long> {

    Page<IncomeTransaction> findAllByCategoryId(Long categoryId , Pageable pageable);

    Page<IncomeTransaction> findAllByAccountId(Long accountId , Pageable pageable);

    Page<IncomeTransaction> findAllByUserBudgetId(Long budgetId , Pageable pageable);


}
