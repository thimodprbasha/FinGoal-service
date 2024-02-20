package com.example.fingoal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
//TODO add json property name later
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryDto {

    private Long id;

    private String categoryName;

    private String icon;

    private BigDecimal setAmount;

    private Long budgetId;

}
