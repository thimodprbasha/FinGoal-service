package com.example.fingoal.service.impl;

import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAllUsersByRole(Role role , Pageable pageable) {
        return userRepository.findAllByRole(role , pageable);

    }
}

