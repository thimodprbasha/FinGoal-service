package com.example.fingoal.service.impl;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.dto.AuthenticationResponseDto;
import com.example.fingoal.model.Role;
import com.example.fingoal.model.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.AuthenticationService;
import com.example.fingoal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public User register(RegisterRequestDto registerRequestDto){
        User user = User.builder()
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .telephone(registerRequestDto.getTelephone())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(Role.USER)
                .profilePicture(registerRequestDto.getProfilePicture())
                .build();
        return userRepository.save(user);

    }
    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.getEmail() , request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        AuthenticationResponseDto jwtAuthResponse = AuthenticationResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return jwtAuthResponse;

    }

    @Override
    public AuthenticationResponseDto refreshToken(String refreshToken) {
        String userEmail = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshToken, user)){
            var accessToken = jwtService.generateToken(user);

            AuthenticationResponseDto jwtAuthResponse = AuthenticationResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return jwtAuthResponse;
        }
        return null;
    }

}
