package com.example.fingoal.repository;

import com.example.fingoal.model.budget.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer , Long> {

    Page<Transfer> findAllByUserBudgetId(Long userBidgetId, Pageable pageable);

    Page<Transfer> findAllByFromAccountId(Long accountId , Pageable pageable);

    Page<Transfer> findByToAccountId(Long accountId , Pageable pageable);

}
