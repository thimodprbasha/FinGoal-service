package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String accountName;

    private String accountType;

    private String accountNumber;

    private BigDecimal balance;
}
