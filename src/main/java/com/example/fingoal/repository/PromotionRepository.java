package com.example.fingoal.repository;

import com.example.fingoal.model.merchant.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
