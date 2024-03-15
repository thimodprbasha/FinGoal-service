package com.example.fingoal.repository;

import com.example.fingoal.model.merchant.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Page<Promotion> findAllByMerchantId(Long merchantId, Pageable pageable);

    @Query(
            "SELECT p FROM Promotion p " +
            "WHERE p.startDate <= :currentDate AND p.endDate >= :currentDate " +
            "ORDER BY p.createdAt DESC"
    )
    Page<Promotion> findAllValidPromotions(
            @Param("currentDate") LocalDate currentDate ,
            Pageable pageable
    );
}
