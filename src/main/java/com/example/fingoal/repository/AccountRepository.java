package com.example.fingoal.repository;

import com.example.fingoal.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {
}
