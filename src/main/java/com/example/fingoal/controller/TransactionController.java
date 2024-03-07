package com.example.fingoal.controller;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.impl.IncomeTransactionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping("/api/v1/user/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final IncomeTransactionServiceImpl incomeTransactionService;

    private final BudgetService budgetService;

    @PostMapping("/create/{user-id}")
    public ResponseEntity<?> createIncomeTransaction(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody IncomeTransactionDto incomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(id);
        IncomeTransactionDto response = incomeTransactionService.createTransaction(incomeTransactionDto , userBudget);

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
