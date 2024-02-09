package com.example.fingoal.service;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.model.User;

public interface AuthenticationService {
    User register(AuthenticationRequestDto authenticationRequestDto);
}
