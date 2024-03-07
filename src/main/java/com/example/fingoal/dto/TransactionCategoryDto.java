package com.example.fingoal.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//TODO add json property name later
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryDto {

    private Long id;

    private Long userBudgetId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String categoryName;

    private String icon;

    private BigDecimal setAmount;

    private BigDecimal currentAmount;


}
