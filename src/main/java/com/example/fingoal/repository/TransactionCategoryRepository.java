package com.example.fingoal.repository;

import com.example.fingoal.model.TransactionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory , Long> {

    Page<TransactionCategory> findAllByUserBudgetId(Long id , Pageable pageable);

    Optional<TransactionCategory> findByCategoryName(String categoryName);
}
