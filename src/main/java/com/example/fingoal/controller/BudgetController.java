package com.example.fingoal.controller;

import com.example.fingoal.dto.RequestUserBudgetDto;
import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.ResponseUserBudgetDto;
import com.example.fingoal.model.User;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
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

@RestController
@RequestMapping("/api/v1/user/budget")

@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class BudgetController {

    private final UserService userService;

    private final BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<?> createBudget(
            @Valid
            @RequestBody ResponseUserBudgetDto budget,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.findUser(userDetails.getUsername());
        ResponseUserBudgetDto response = budgetService.createBudget(budget , user);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/get/{user-id}")
    public ResponseEntity<?> getBudget(
            @Valid
            @PathVariable(name = "user-id") long id
    ) {
        ResponseUserBudgetDto response = budgetService.findUserBudgetByUserMapToDto(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-all-transactions/{user-id}")
    public ResponseEntity<?> getAllTransactions(
            @Valid
            @PathVariable(name = "user-id") long id,
            @RequestParam(name = "pageNo" , defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize" , defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        Long userBudgetId = budgetService.findUserBudgetByUser(id).getId();
        Page<TransactionDto> response = budgetService.findAllIncomeAndOutcomeTransactions(userBudgetId ,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{user-id}")
    public ResponseEntity<?> updateBudget(
            @Valid
            @PathVariable(name = "user-id") long userId,
            @RequestBody RequestUserBudgetDto requestUserBudgetDto
    ) {
        if (requestUserBudgetDto.hasAtLeastOneValue()){
            ResponseUserBudgetDto response = budgetService.updateUserBudget(userId , requestUserBudgetDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
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
