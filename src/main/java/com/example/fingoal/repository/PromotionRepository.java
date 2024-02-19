package com.example.fingoal.repository;

import com.example.fingoal.model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotions , Long> {
}
