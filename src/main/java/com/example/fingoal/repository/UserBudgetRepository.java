package com.example.fingoal.repository;

import com.example.fingoal.model.UserBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBudgetRepository extends JpaRepository<UserBudget , Long> {
}
