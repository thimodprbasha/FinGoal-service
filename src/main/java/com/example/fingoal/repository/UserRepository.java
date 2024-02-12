package com.example.fingoal.repository;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User , Long> , PagingAndSortingRepository<User , Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);

    Page<User> findAllByRole(Role role , Pageable pageable);
}