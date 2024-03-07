package com.example.fingoal.controller;

import com.example.fingoal.dto.UserBudgetDto;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
