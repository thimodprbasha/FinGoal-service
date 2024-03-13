package com.example.fingoal.controller;

import com.example.fingoal.dto.IncomeTransactionDto;
import com.example.fingoal.dto.OutcomeTransactionDto;
import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.TransferDto;
import com.example.fingoal.model.*;
import com.example.fingoal.service.budgetService.BudgetService;
import com.example.fingoal.service.budgetService.TransactionService;
import com.example.fingoal.service.budgetService.impl.IncomeTransactionServiceImpl;
import com.example.fingoal.service.budgetService.impl.OutcomeTransactionServiceImpl;
import com.example.fingoal.service.budgetService.impl.TransferTransactionServiceImpl;
import com.example.fingoal.service.merchantService.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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

    private final TransferTransactionServiceImpl transferTransactionService;

    private final BudgetService budgetService;

    private final MerchantService merchantService;

    @PostMapping("/income/create")
    public ResponseEntity<IncomeTransactionDto> createIncomeTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody IncomeTransactionDto incomeTransactionDto
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(((User)userDetails).getId());
        IncomeTransactionDto response = incomeTransactionService.createTransaction(incomeTransactionDto , userBudget , null);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("income/get-by/{id}")
    public ResponseEntity<TransactionDto> getIncomeTransaction(
            @Valid
            @PathVariable(name = "id") long transactionId
    ){
        IncomeTransactionDto response = incomeTransactionService.findTransactionByIdMapToDto(transactionId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("income/get-all")
    public ResponseEntity<Page<IncomeTransactionDto>> getAllIncomeTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "accountId") Optional<Long> accountId

    ){
        User conectedUser = (User) userDetails;
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        Page<IncomeTransactionDto> response = handleTransactionByParameter(
                incomeTransactionService ,
                Optional.empty(),
                Optional.empty(),
                accountId,
                conectedUser.getUserBudget().getId(),
                pageable
        );
        return new ResponseEntity<>(
                response ,
                HttpStatus.OK
        );
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

    @GetMapping("/outcome/get-by/{id}")
    public ResponseEntity<TransactionDto> getOutcomeTransaction(
            @Valid
            @PathVariable(name = "id") long transactionId
    ){
        OutcomeTransactionDto response = outcomeTransactionService.findTransactionByIdMapToDto(transactionId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/outcome/get-all")
    public ResponseEntity<Page<OutcomeTransactionDto>> getAllOutcomeTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "merchantId") Optional<Long> merchantId,
            @RequestParam(name = "categoryId") Optional<Long> categoryId,
            @RequestParam(name = "accountId") Optional<Long> accountId

    ){
        User conectedUser = (User) userDetails;
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        Page<OutcomeTransactionDto> response = handleTransactionByParameter(
                outcomeTransactionService,
                merchantId,
                categoryId,
                accountId,
                conectedUser.getUserBudget().getId(),
                pageable
        );
        return new ResponseEntity<>(
                response ,
                HttpStatus.OK
        );
    }

    @PostMapping("/transfer/create")
    public ResponseEntity<TransferDto> createTransferTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TransferDto requestTransfer
    ) {
        UserBudget userBudget = budgetService.findUserBudgetByUser(((User)userDetails).getId());
        TransferDto response = transferTransactionService.createTransaction(requestTransfer , userBudget , null);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/transfer/get-by/{id}")
    public ResponseEntity<TransactionDto> getTransferTransaction(
            @Valid
            @PathVariable(name = "id") long transactionId
    ){
        TransferDto response = transferTransactionService.findTransactionByIdMapToDto(transactionId);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/transfer/get-all")
    public ResponseEntity<Page<TransferDto>> getAllTransferTransaction(
            @Valid
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "accountId") Optional<Long> accountId

    ){
        User conectedUser = (User) userDetails;
        Pageable pageable =  PageRequest.of(pageNo ,pageSize);
        Page<TransferDto> response = handleTransactionByParameter(
                transferTransactionService,
                Optional.empty(),
                Optional.empty(),
                accountId,
                conectedUser.getUserBudget().getId(),
                pageable
        );
        return new ResponseEntity<>(
                response ,
                HttpStatus.OK
        );
    }




    private <S,D> Page<D> handleTransactionByParameter(
            TransactionService<S,D> transactionService,
            Optional<Long> merchantId,
            Optional<Long> categoryId,
            Optional<Long> accountId,
            Long userId,
            Pageable pageable
    ){
        if (merchantId.isPresent()){
            return transactionService.getAllTransactionByMerchant(merchantId.get() , pageable);
        } else if (categoryId.isPresent()) {
            return transactionService.getAllTransactionByCategory(categoryId.get() , pageable);
        } else if (accountId.isPresent()) {
            return transactionService.getAllTransactionByAccount(accountId.get() , pageable);
        }else {
            return transactionService.getAllTransactionByBudget(userId , pageable);
        }
    }
}
