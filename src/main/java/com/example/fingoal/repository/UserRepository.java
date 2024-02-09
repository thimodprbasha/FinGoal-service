package com.example.fingoal.repository;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);


}