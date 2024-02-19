package com.example.fingoal.controller;
import com.example.fingoal.dto.BudgetDto;
import com.example.fingoal.dto.CategoryDto;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final BudgetService budgetService;

    @PostMapping("/create-budget/{user-id}")
    public ResponseEntity<?> createBudget(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody BudgetDto budget
    ) {
       budget.setUserId(id);
       var resp = budgetService.createBudget(budget);
       return new ResponseEntity(resp , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-budget/{user-id}")
    public ResponseEntity<?> deleteBudget(
            @Valid
            @PathVariable(name = "user-id") long id
    ) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/create-transaction-category/{user-id}")
    public ResponseEntity<?> createCategory(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody CategoryDto categoryDto
    ) {
        var resp = budgetService.createTransactionCategoryByUser(categoryDto , id);
        return new ResponseEntity(resp , HttpStatus.CREATED);
    }
}
