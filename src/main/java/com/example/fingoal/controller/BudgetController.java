package com.example.fingoal.controller;

import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final UserService userService;

    private final BudgetService budgetService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<?> createBudget(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody UserBudgetDto budget
    ) {
//        budget.setUserId(id);
        var user = userService.isUserExist(id);
        var resp = budgetService.createBudget(budget , user);
        return new ResponseEntity<>(resp , HttpStatus.CREATED);
    }

    @GetMapping("/get/{user-id}")
    public ResponseEntity<?> getBudget(
            @Valid
            @PathVariable(name = "user-id") long id
    ) {
        var resp = budgetService.findUserBudgetByUserMapToDto(id);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/get-all-transactions/{user-id}")
    public ResponseEntity<?> getAllTransactions(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize , Sort.by("transactionDate").ascending());
        var userBudgetId = budgetService.findUserBudgetByUser(id).getId();
        Page<TransactionDto> response = budgetService.findAllIncomeAndOutcomeTransactions(userBudgetId ,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{user-id}")
    public ResponseEntity<?> updateBudget(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestBody UserBudgetDto userBudgetDto
    ) {
        var user = userService.isUserExist(id);
        var updatedBudget = budgetService.updateUserBudget(userBudgetDto, user);
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity<?> deleteBudget(
            @Valid
            @PathVariable(name = "user-id") long id
    ) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok().body("ok");
    }
}
