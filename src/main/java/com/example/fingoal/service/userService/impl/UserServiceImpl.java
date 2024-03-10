package com.example.fingoal.service.userService.impl;

import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a User with %S" , username ) , HttpStatus.NOT_FOUND));
    }

    @Override
    public User isUserExist(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(String.format("Could not find a User with %S" , email )  , HttpStatus.NOT_FOUND));
    }

    @Override
    public User isUserExist(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find a User with ID : %d" , id )  , HttpStatus.NOT_FOUND));

    }

}
