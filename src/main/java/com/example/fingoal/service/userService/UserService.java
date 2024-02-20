package com.example.fingoal.service.userService;

import com.example.fingoal.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();

    User isUserExist(String email);

    User isUserExist(Long id);


}
