package com.example.fingoal.repository;

import com.example.fingoal.model.merchant.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category ,Long> {
}
