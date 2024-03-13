package com.example.fingoal.controller;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.service.merchantService.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
@RequestMapping("/api/v1/user/merchant")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MERCHANT')")
public class MerchantController {

    private final MerchantService merchantService;
    @PostMapping("/create")
    public ResponseEntity<?> createMerchant(
            @Valid
            @RequestBody MerchantDto merchantDto
    ) {
        return new ResponseEntity<>(merchantService.createMerchant(merchantDto), HttpStatus.CREATED);
    }
}
