package com.example.fingoal.controller;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.model.User;
import com.example.fingoal.service.budgetService.AccountService;
import com.example.fingoal.service.userService.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/account")
@PreAuthorize("hasAuthority('USER')")
public class AccountController {
    private final UserService userService;

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(
            @Valid
            @RequestBody AccountDto accountDto ,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findUser(((User)userDetails).getId());
        var resp = accountService.createAccount(accountDto, user);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<AccountDto> updateAccount(
            @Valid
            @RequestBody AccountDto accountDto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findUser(((User)userDetails).getId());
        AccountDto resp = accountService.updateAccount(accountDto, user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<AccountDto>> getAllAccounts(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AccountDto> accounts = accountService.getAllAccountsByUser(((User)userDetails).getId(), pageable);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/get-by")
    public ResponseEntity<?> getAllAccounts(
            @Valid
            @RequestParam(name = "account-id", required = false) Optional<Long> accountId,
            @RequestParam(name = "account-number", required = false) String accountNumber
    ) {

        if (accountId.isPresent()) {
            return new ResponseEntity<>(accountService.findAccountByIdMapToDto(accountId.get()), HttpStatus.OK);
        } else if (StringUtils.isNotEmpty(accountNumber)) {
            return new ResponseEntity<>(accountService.findAccountByNumber(accountNumber), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Invalid");
    }


    @DeleteMapping("/delete/{account-id}")
    public ResponseEntity<?> deleteAccount(
            @Valid
            @PathVariable(name = "account-id") long accountId
    ) {
        accountService.deleteAccount(accountId);
        return  ResponseEntity.ok().build();
    }
}
