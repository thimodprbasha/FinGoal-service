package com.example.fingoal.repository;

import com.example.fingoal.model.customer.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {

    Optional<Account> findByUserId(Long userId);
    Optional<Account> findByAccountNumber(String accountNumber);
    Page<Account> findAllByUserId(Long userId , Pageable pageable);
}
