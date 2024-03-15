package com.example.fingoal.controller;

import com.example.fingoal.dto.AuthenticationRequestDto;
import com.example.fingoal.dto.JwtDto;
import com.example.fingoal.dto.RegisterRequestDto;
import com.example.fingoal.model.users.Role;
import com.example.fingoal.model.users.User;
import com.example.fingoal.service.authenticationService.AuthenticationService;
import com.example.fingoal.service.userService.UserService;
import com.example.fingoal.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;


    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(
            @Valid
            @RequestBody  RegisterRequestDto requestDto
    ) {
        return createUser(requestDto , Role.USER);
    }

    @PostMapping("/merchant/register")
    public ResponseEntity<?> registerUserMerchant(
            @Valid
            @RequestBody  RegisterRequestDto requestDto
    ) {
        return createUser(requestDto , Role.MERCHANT);
    }

    private ResponseEntity<?> createUser(RegisterRequestDto requestDto , Role role){
        if (userService.isUserAlreadyExist(requestDto.getEmail())){
            return new ResponseEntity<>(
                    Utils.ResponseBody(HttpStatus.FORBIDDEN , "User Already Exists"),
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authenticationService.register(requestDto , role),HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticate(
            @Valid
            @RequestBody AuthenticationRequestDto request
    ) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        ));
        User user = (User) authentication.getPrincipal();
        if (user.isNotEnabled()){
            return new ResponseEntity<>(
                    Utils.ResponseBody(HttpStatus.FORBIDDEN , "Account is disabled"),
                    HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtDto> refreshToken(
            @Valid
            @RequestBody JwtDto refreshToken
    ) {
        return ResponseEntity.ok(authenticationService.refreshToken(""));
    }


}