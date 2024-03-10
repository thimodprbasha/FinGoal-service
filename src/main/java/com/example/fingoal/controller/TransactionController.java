package com.example.fingoal.controller;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.model.Merchant;
import com.example.fingoal.model.UserBudget;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.impl.IncomeTransactionServiceImpl;
import com.example.fingoal.service.budgetService.impl.OutcomeTransactionServiceImpl;
import com.example.fingoal.service.merchantService.MerchantService;
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

    private final OutcomeTransactionServiceImpl outcomeTransactionService;

    private final BudgetService budgetService;

    private final MerchantService merchantService;

    @PostMapping("/income/create/{user-id}")
    public ResponseEntity<?> createIncomeTransaction(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody IncomeTransactionDto incomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(id);
        IncomeTransactionDto response = incomeTransactionService.createTransaction(incomeTransactionDto , userBudget , null);

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PostMapping("/outcome/create/{user-id}")
    public ResponseEntity<?> createOutcomeTransaction(
            @Valid
            @PathVariable(name = "user-id") long id ,
            @RequestBody OutcomeTransactionDto outcomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(id);
        Merchant merchant = merchantService.findMerchantById(outcomeTransactionDto.getMerchantId());
        OutcomeTransactionDto response = outcomeTransactionService.createTransaction(outcomeTransactionDto , userBudget , merchant );

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
