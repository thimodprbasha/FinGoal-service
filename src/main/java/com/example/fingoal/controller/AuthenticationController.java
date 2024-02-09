package com.example.fingoal.controller;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.AuthenticationResponseDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.model.User;
import com.example.fingoal.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto requestDto) {
        return ResponseEntity.ok(authenticationService.register(requestDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(@RequestParam("refresh_token") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }


}