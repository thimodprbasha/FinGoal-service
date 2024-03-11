package com.example.fingoal.service.authenticationService;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.JwtDto;
import com.example.fingoal.dto.RegisterRequestDto;

public interface AuthenticationService {
    JwtDto register(RegisterRequestDto registerRequestDto);

    JwtDto authenticate(AuthenticationRequestDto request);

    JwtDto refreshToken(String refreshToken);
}
