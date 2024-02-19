package com.example.fingoal.service.userService;

import com.example.fingoal.dto.UserDto;
import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminService {

    Page<UserDto> getAllUsersByRole(Role role , Pageable pageable);

}
