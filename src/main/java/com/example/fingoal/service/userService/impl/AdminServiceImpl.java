package com.example.fingoal.service.userService.impl;

import com.example.fingoal.dto.UserDto;
import com.example.fingoal.mappers.impl.UserMapper;
import com.example.fingoal.model.Role;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.userService.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    @Override
    public Page<UserDto> getAllUsersByRole(Role role , Pageable pageable) {
        return userRepository.findAllByRole(role , pageable).map(mapper::mapTo);
    }
}

