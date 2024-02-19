package com.example.fingoal.service.userService.impl;

import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }

    @Override
    public Optional<User> isUserExist(String email) {
        var user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Not found");
        }
        return user;
    }

    @Override
    public Optional<User> isUserExist(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Not found");
        }
        return user;
    }

}
