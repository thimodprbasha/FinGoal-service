package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.TransferDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.TransferMapper;
import com.example.fingoal.model.budget.TransactionType;
import com.example.fingoal.model.budget.Transfer;
import com.example.fingoal.model.budget.UserBudget;
import com.example.fingoal.model.customer.Account;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.repository.TransferRepository;
import com.example.fingoal.service.budgetService.TransactionService;
import com.example.fingoal.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class TransferTransactionServiceImpl implements TransactionService<Transfer, TransferDto> {

    private final TransferMapper mapper;

    private final TransferRepository transferRepository;
    @Override
    @Transactional
    public TransferDto createTransaction(TransferDto requestTransfer, UserBudget userBudget, Merchant merchant) {
        Account senderAccount = Utils
                .GetAccountFromBudgetEntity(
                        userBudget.getUser().getAccounts().stream() , requestTransfer.getFromAccount()
                );
        Account receiverAccount = Utils
                .GetAccountFromBudgetEntity(
                        userBudget.getUser().getAccounts().stream() , requestTransfer.getToAccount()
                );

        BigDecimal deductedAmountSenderAcc = senderAccount.getBalance().subtract(requestTransfer.getAmount());
        //fixme
        if (deductedAmountSenderAcc.signum() == -1){
            throw new RuntimeException("cant be zweo");
        }
        BigDecimal incrementedAmountReceiverAcc = receiverAccount.getBalance().add(requestTransfer.getAmount());

        receiverAccount.setBalance(deductedAmountSenderAcc);
        senderAccount.setBalance(incrementedAmountReceiverAcc);

        Transfer transfer = mapper.mapFrom(requestTransfer);
        transfer.setTransactionType(TransactionType.TRANSFER);
        transfer.setFromAccount(senderAccount);
        transfer.setToAccount(receiverAccount);

        Transfer saved = transferRepository.save(transfer);
        return mapper.mapTo(saved);
    }

    @Override
    public Transfer findTransactionById(Long transferId) {
        return transferRepository
                .findById(transferId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Transfer with ID : %d" , transferId )
                                , HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public TransferDto findTransactionByIdMapToDto(Long transferId) {
        return mapper.mapTo(this.findTransactionById(transferId));
    }

    @Override
    public Page<TransferDto> getAllTransactionByAccount(Long accountId, Pageable pageable) {
        return transferRepository.findAllByFromAccountId(accountId , pageable).map(mapper::mapTo);
    }

    @Override
    public Page<TransferDto> getAllTransactionByBudget(Long budgetId, Pageable pageable) {
        return transferRepository.findAllByUserBudgetId(budgetId , pageable).map(mapper::mapTo);
    }
}
