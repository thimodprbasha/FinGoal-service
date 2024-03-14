package com.example.fingoal.repository;

import com.example.fingoal.model.budget.Transaction;
import com.example.fingoal.model.budget.UserBudget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBudgetRepository extends JpaRepository<UserBudget, Long> {
    Optional<UserBudget> findByUserId(Long userId);

    @Query("SELECT DISTINCT t " +
            "FROM UserBudget ub " +
            "LEFT JOIN ub.incomeTransactions ib " +
            "LEFT JOIN ub.outcomeTransactions ot " +
            "LEFT JOIN ub.transfers ti " +
            "LEFT JOIN Transaction t ON (t.id = ib.id OR t.id = ot.id OR t.id = ti.id) " +
            "WHERE ub.id = :userBudgetId " +
            "ORDER BY t.transactionDate DESC"
    )
    Page<Transaction> findIncomeAndOutcomeTransactionsByUserBudgetId(@Param("userBudgetId") Long userBudgetId, Pageable pageable);

}
