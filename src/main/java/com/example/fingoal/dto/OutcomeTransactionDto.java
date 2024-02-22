package com.example.fingoal.dto;

import com.example.fingoal.model.Merchant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeTransactionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime transactionDate;

    private BigDecimal amount;

    private String remarks;

    private String attachment;

    private Long accountId;

    private Long categoryId;

    private Long userBudgetId;

    private Long merchantId;
}
