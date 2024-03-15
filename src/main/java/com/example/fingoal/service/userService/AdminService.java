package com.example.fingoal.service.userService;

import com.example.fingoal.dto.UserDto;
import com.example.fingoal.model.users.Role;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<UserDto> getAllUsersByRole(Role role , Pageable pageable);

    Page<UserDto> getAllUsers(Pageable pageable);
}
