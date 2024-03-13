package com.example.fingoal.controller;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.model.Merchant;
import com.example.fingoal.model.User;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/transaction")
@PreAuthorize(
        "hasAuthority('USER') and " +
        "@securityAuthorizeHandler.ifBudgetPresent(authentication)"
)
public class TransactionController {

    private final IncomeTransactionServiceImpl incomeTransactionService;

    private final OutcomeTransactionServiceImpl outcomeTransactionService;

    private final BudgetService budgetService;

    private final MerchantService merchantService;

    @PostMapping("/income/create")
    public ResponseEntity<?> createIncomeTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody IncomeTransactionDto incomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(((User)userDetails).getId());
        IncomeTransactionDto response = incomeTransactionService.createTransaction(incomeTransactionDto , userBudget , null);

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PostMapping("/outcome/create")
    public ResponseEntity<?> createOutcomeTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody OutcomeTransactionDto outcomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(((User)userDetails).getId());
        Merchant merchant = merchantService.findMerchantById(outcomeTransactionDto.getMerchantId());
        OutcomeTransactionDto response = outcomeTransactionService.createTransaction(outcomeTransactionDto , userBudget , merchant );

        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }
}
