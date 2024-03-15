package com.example.fingoal.repository;

import com.example.fingoal.model.budget.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long>{

}
