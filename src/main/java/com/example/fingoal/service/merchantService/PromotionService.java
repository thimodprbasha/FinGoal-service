package com.example.fingoal.service.merchantService;

import com.example.fingoal.dto.PromotionDto;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.merchant.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {

    PromotionDto createPromotion(PromotionDto promotionDto , Merchant merchant);

    PromotionDto updatePromotion(Long promotionId , PromotionDto promotionDto);

    Promotion findPromotionById(Long promotionId);

    PromotionDto findPromotionByIdMapToDto(Long promotionId);

    Page<PromotionDto> findAllPromotionsByMerchant(Long merchantId , Pageable pageable);

    Page<PromotionDto> getAllPromotion(Pageable pageable);

    Page<PromotionDto> getAllValidPromotion(Pageable pageable);

    void deletePromotion(Promotion promotionDto);

    void deletePromotion(Long promotionId);
}
