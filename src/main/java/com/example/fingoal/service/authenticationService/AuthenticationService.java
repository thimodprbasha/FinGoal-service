package com.example.fingoal.service.authenticationService;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.JwtDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.model.users.Role;

public interface AuthenticationService {
    JwtDto register(RegisterRequestDto registerRequestDto , Role role);

    JwtDto authenticate(AuthenticationRequestDto request);

    JwtDto refreshToken(String refreshToken);
}
