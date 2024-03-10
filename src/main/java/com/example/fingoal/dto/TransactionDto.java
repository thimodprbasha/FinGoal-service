package com.example.fingoal.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TransactionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate transactionDate;

    private BigDecimal amount;

    private String remarks;

    private String attachment;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
