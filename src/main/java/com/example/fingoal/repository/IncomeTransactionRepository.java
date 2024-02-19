package com.example.fingoal.repository;

import com.example.fingoal.model.IncomeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeTransactionRepository extends JpaRepository<IncomeTransaction , Long> {
}
