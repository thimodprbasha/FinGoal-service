package com.example.fingoal.controller;

import com.example.fingoal.dto.AccountDto;
import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.service.budgetService.AccountService;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionCategoryService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/account")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    private final BudgetService budgetService;

    private final AccountService accountService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<?> createAccount(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody AccountDto accountDto
    ) {
        var user = userService.isUserExist(id);
        var resp = accountService.createAccount(accountDto , user);
        return new ResponseEntity(resp , HttpStatus.CREATED);
    }
}
