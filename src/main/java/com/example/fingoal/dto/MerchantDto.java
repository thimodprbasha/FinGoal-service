package com.example.fingoal.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String merchantName;

    private String merchantCategory;

    private String location;

    private List<OutcomeTransactionDto> outcomeTransactions;

    private List<PromotionDto> promotions;
}
