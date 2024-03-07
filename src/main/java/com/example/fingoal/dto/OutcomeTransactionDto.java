package com.example.fingoal.dto;

import com.example.fingoal.model.Merchant;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeTransactionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate transactionDate;

    private BigDecimal amount;

    private String remarks;

    private String attachment;

    private Long accountId;

    private Long categoryId;

    private Long userBudgetId;

    private Long merchantId;
}
