package com.example.fingoal.controller;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.dto.PromotionDto;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.users.User;
import com.example.fingoal.service.merchantService.MerchantService;
import com.example.fingoal.service.merchantService.impl.MerchantServiceImpl;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('MERCHANT','ADMIN','USER')")
public class MerchantController {

    private final MerchantServiceImpl merchantService;

    private final UserService userService;

    @PostMapping("/merchant/create-store")
    @PreAuthorize("hasAuthority('MERCHANT')")
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


    @PutMapping("/merchant/update-store/{merchant-id}")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> updateMerchant(
            @Valid
            @RequestBody MerchantDto merchantDto,
            @PathVariable("merchant-id") Long merchantId
    ) {
        MerchantDto response = merchantService.updateMerchant(merchantId , merchantDto );
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-merchants")
    @PreAuthorize("hasAnyAuthority('MERCHANT','ADMIN','USER')")
    public ResponseEntity<?> getAllMerchant(
            @Valid
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        return new ResponseEntity<>(
                merchantService.getAllMerchant(pageable)
                , HttpStatus.OK
        );
    }

    @DeleteMapping("/merchant/delete-store/{merchant-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('MERCHANT')")
    public void deleteMerchant(
            @Valid
            @PathVariable(name = "merchant-id") long merchantId
    ) {
        merchantService.deleteMerchant(merchantId);
    }

    @PostMapping("/merchant/create-promotion/{merchant-id}")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> createPromotion(
            @Valid
            @RequestBody PromotionDto promotionDto,
            @PathVariable(name = "merchant-id") long merchantId
    ) {
        Merchant merchant = merchantService.findMerchantById(merchantId);
        PromotionDto response = merchantService.createPromotion(promotionDto , merchant);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/merchant/update-promotion/{promotion-id}")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> updatePromotion(
            @Valid
            @RequestBody PromotionDto promotionDto,
            @PathVariable(name = "promotion-id") long promotionId
    ) {
        PromotionDto response = merchantService.updatePromotion(promotionId , promotionDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/merchant/get-all-promotions")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> getAllPromotions(
            @Valid
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        return new ResponseEntity<>(
                merchantService.getAllPromotion(pageable)
                , HttpStatus.OK
        );
    }

    @GetMapping("/get-all-valid-promotions")
    @PreAuthorize("hasAnyAuthority('MERCHANT','ADMIN','USER')")
    public ResponseEntity<?> getAllValidPromotions(
            @Valid
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        return new ResponseEntity<>(
                merchantService.getAllValidPromotion(pageable)
                , HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/promotion/{promotion-id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('MERCHANT')")
    public void deletePromotion(
            @Valid
            @PathVariable(name = "promotion-id") long promotionId
    ) {
        merchantService.deletePromotion(promotionId);
    }
}
