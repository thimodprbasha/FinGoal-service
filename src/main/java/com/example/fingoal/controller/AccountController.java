package com.example.fingoal.controller;

import com.example.fingoal.dto.AccountDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    private final AccountService accountService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<AccountDto> createAccount(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestBody AccountDto accountDto
    ) {
        var user = userService.isUserExist(id);
        var resp = accountService.createAccount(accountDto, user);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{user-id}")
    public ResponseEntity<AccountDto> updateAccount(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestBody AccountDto accountDto
    ) {
        var user = userService.isUserExist(id);
        var resp = accountService.updateAccount(accountDto, user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/get-all/{user-id}")
    public ResponseEntity<Page<AccountDto>> getAllAccounts(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        var user = userService.isUserExist(id);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AccountDto> accounts = accountService.getAllAccountsByUser(user.getId(), pageable);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/get-by")
    public ResponseEntity<?> getAllAccounts(
            @Valid
            @RequestParam(name = "account-id", required = false) Optional<Long> accountId,
            @RequestParam(name = "account-number", required = false) String accountNumber
    ) {

        if (accountId.isPresent()) {
            return new ResponseEntity<>(accountService.accountFindById(accountId.get()), HttpStatus.OK);
        } else if (StringUtils.isNotEmpty(accountNumber)) {
            return new ResponseEntity<>(accountService.accountFindByNumber(accountNumber), HttpStatus.OK);
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
