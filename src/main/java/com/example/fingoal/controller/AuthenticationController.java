package com.example.fingoal.controller;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.model.User;
import com.example.fingoal.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController implements CommandLineRunner {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody AuthenticationRequestDto requestDto){
        return ResponseEntity.ok(authenticationService.register(requestDto));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
