package com.example.fingoal.service.impl;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(AuthenticationRequestDto authenticationRequestDto){
        User user = User.builder()
                .firstName(authenticationRequestDto.getFirstName())
                .lastName(authenticationRequestDto.getLastName())
                .telephone(authenticationRequestDto.getTelephone())
                .email(authenticationRequestDto.getEmail())
                .password(passwordEncoder.encode(authenticationRequestDto.getPassword()))
                .role(Role.USER)
                .profilePicture(authenticationRequestDto.getProfilePicture())
                .build();
        return userRepository.save(user);

    }
}
