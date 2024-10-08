package com.example.fingoal.repository;

import com.example.fingoal.model.merchant.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Page<Merchant> findAllByUserId(Long userId , Pageable pageable);


}
