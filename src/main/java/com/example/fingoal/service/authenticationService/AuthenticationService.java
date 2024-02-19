package com.example.fingoal.service.authenticationService;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.AuthenticationResponseDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.model.User;

public interface AuthenticationService {
    AuthenticationResponseDto register(RegisterRequestDto registerRequestDto);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);

    AuthenticationResponseDto refreshToken(String refreshToken);
}
