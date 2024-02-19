package com.example.fingoal.repository;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);

    Page<User> findAllByRole(Role role , Pageable pageable);
}