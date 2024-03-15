package com.example.fingoal.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String promotionName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long merchantId;
}
