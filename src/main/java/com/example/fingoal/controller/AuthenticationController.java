package com.example.fingoal.controller;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.AuthenticationResponseDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody  RegisterRequestDto requestDto ) {
        return new ResponseEntity(authenticationService.register(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@Valid @RequestBody AuthenticationRequestDto request) {
        return new ResponseEntity(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponseDto> refreshToken(@Valid @RequestParam("refresh_token") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }


}