package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//TODO add json property name later
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String categoryName;

    private String icon;

    private BigDecimal setAmount;

    private Long budgetId;

}
