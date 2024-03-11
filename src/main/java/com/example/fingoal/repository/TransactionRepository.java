package com.example.fingoal.repository;

import com.example.fingoal.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long>{
    Page<Transaction> getAllByUserBudgetId(Long userBudgetId , Pageable pageable);

}
