package com.example.fingoal.service;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminService {

    Page<User> getAllUsersByRole(Role role , Pageable pageable);

}
