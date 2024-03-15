package com.example.fingoal.service.userService;

import com.example.fingoal.model.users.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User findUser(String email);

    Boolean isUserAlreadyExist(String email);

    User findUser(Long id);


}
