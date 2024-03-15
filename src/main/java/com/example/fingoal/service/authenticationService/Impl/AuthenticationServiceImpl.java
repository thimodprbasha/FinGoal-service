package com.example.fingoal.service.authenticationService.Impl;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.dto.JwtDto;
import com.example.fingoal.model.users.Role;
import com.example.fingoal.model.users.User;
import com.example.fingoal.repository.UserRepository;
import com.example.fingoal.service.authenticationService.AuthenticationService;
import com.example.fingoal.service.jwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    public JwtDto register(RegisterRequestDto registerRequestDto , Role role){
        User user = User.builder()
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .telephone(registerRequestDto.getTelephone())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(role)
                .profilePicture(registerRequestDto.getProfilePicture())
                .build();

        userRepository.save(user);
        return generateAuthenticationResponse(user);

    }
    @Override
    public JwtDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.getEmail() , request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        return generateAuthenticationResponse(user);

    }

    @Override
    public JwtDto refreshToken(String refreshToken) {
        String userEmail = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshToken, user)){
            var accessToken = jwtService.generateToken(user);

            JwtDto jwtAuthResponse = JwtDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return jwtAuthResponse;
        }
        return null;
    }

    private JwtDto generateAuthenticationResponse(UserDetails user){
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return JwtDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
