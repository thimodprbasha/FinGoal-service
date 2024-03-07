package com.example.fingoal.repository;

import com.example.fingoal.model.IncomeTransaction;
import com.example.fingoal.model.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBudgetRepository extends JpaRepository<UserBudget , Long> {
    Optional<UserBudget> findByUserId(Long userId);

//    Optional<UserBudget> update

//    Page<IncomeTransaction> getAllByIncomeTransactions(Pageable pageable);
}
