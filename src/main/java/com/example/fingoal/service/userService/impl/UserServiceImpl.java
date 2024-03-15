package com.example.fingoal.service.userService.impl;

import com.example.fingoal.model.users.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return username -> userRepository
                .findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Could not find a User with %S" , username )
                        )
                );
    }

    @Override
    public User findUser(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Could not find a User with %S" , email )
                        )
                );
    }
    @Override
    public Boolean isUserAlreadyExist(String email) {
        return userRepository
                .findByEmail(email)
                .isPresent();
    }

    @Override
    public User findUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Could not find a User with ID : %S" , id )
                        )
                );


    }

}
