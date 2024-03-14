package com.example.fingoal.controller;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.model.users.User;
import com.example.fingoal.service.merchantService.MerchantService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MERCHANT')")
public class MerchantController {

    private final MerchantService merchantService;

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createMerchant(
            @Valid
            @RequestBody MerchantDto merchantDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User connectedUser = (User) userDetails;
        User user = userService.findUser(connectedUser.getId());
        MerchantDto response = merchantService.createMerchant(merchantDto , user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
