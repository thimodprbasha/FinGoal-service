package com.example.fingoal.service.merchantService.impl;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.dto.PromotionDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.MerchantMapper;
import com.example.fingoal.mappers.impl.PromotionMapper;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.merchant.Promotion;
import com.example.fingoal.model.users.User;
import com.example.fingoal.repository.MerchantRepository;
import com.example.fingoal.repository.PromotionRepository;
import com.example.fingoal.service.merchantService.MerchantService;
import com.example.fingoal.service.merchantService.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService , PromotionService {

    private final MerchantRepository merchantRepository;

    private final PromotionRepository promotionRepository;

    private final MerchantMapper merchantMapper;

    private final PromotionMapper promotionMapper;

    @Override
    @Transactional
    public MerchantDto createMerchant(MerchantDto merchantDto , User user) {
        Merchant merchant = merchantMapper.mapFrom(merchantDto);
        merchant.setUser(user);
        return merchantMapper.mapTo(merchantRepository.save(merchant));
    }

    @Override
    @Transactional
    public MerchantDto updateMerchant(Long merchantId , MerchantDto merchantDto) {
        Merchant foundMerchant = this.findMerchantById(merchantId);
        Optional.ofNullable(merchantDto.getMerchantName()).ifPresent(foundMerchant::setMerchantName);
        Optional.ofNullable(merchantDto.getMerchantCategory()).ifPresent(foundMerchant::setMerchantCategory);
        Optional.ofNullable(merchantDto.getLocation()).ifPresent(foundMerchant::setLocation);
        Merchant saved = merchantRepository.save(foundMerchant);
        return merchantMapper.mapTo(saved);
    }

    @Override
    public Merchant findMerchantById(Long merchantId) {
        return merchantRepository
                .findById(merchantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Merchant with ID : '%d'" , merchantId )
                                , HttpStatus.NOT_FOUND
                        )
                );
    }

    @Override
    public Page<MerchantDto> getAllMerchant(Pageable pageable) {
        return merchantRepository.findAll(pageable).map(merchantMapper::mapTo);
    }

    @Override
    public void deleteMerchant(Merchant merchant) {
        merchantRepository.delete(merchant);
    }

    @Override
    public void deleteMerchant(Long merchantId) {
        merchantRepository.deleteById(merchantId);
    }

    @Override
    public MerchantDto findMerchantByIdMapToDto(Long merchantId) {
        return merchantMapper.mapTo(this.findMerchantById(merchantId));
    }

    @Override
    public Page<MerchantDto> findAllMerchantByUser(Long userId , Pageable pageable) {
        return merchantRepository.findAllByUserId(userId , pageable).map(merchantMapper::mapTo);
    }

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto, Merchant merchant) {
        Promotion promotion = promotionMapper.mapFrom(promotionDto);
        promotion.setMerchant(merchant);
        Promotion saved = promotionRepository.save(promotion);
        return promotionMapper.mapTo(saved);
    }

    @Override
    public PromotionDto updatePromotion(Long promotionId , PromotionDto promotionDto) {
//        Promotion foundPromotion = this.findPromotionById(promotionId);
//        Optional.ofNullable(promotionDto.getPromotionName()).ifPresent(foundPromotion::setPromotionName);
//        Optional.ofNullable(merchantDto.getMerchantCategory()).ifPresent(foundMerchant::setMerchantCategory);
//        Optional.ofNullable(merchantDto.getLocation()).ifPresent(foundMerchant::setLocation);
//        Promotion updated = promotionRepository.save(foundPromotion);
//        return promotionMapper.mapTo(updated);
            return null;
    }

    @Override
    public Promotion findPromotionById(Long promotionId) {
          return promotionRepository
                .findById(promotionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Promotion with ID : '%d'" , promotionId )
                                , HttpStatus.NOT_FOUND
                        )
                );
    }

    @Override
    public PromotionDto findPromotionByIdMapToDto(Long promotionId) {
        return promotionMapper.mapTo(this.findPromotionById(promotionId));
    }


    @Override
    public Page<PromotionDto> findAllPromotionsByMerchant(Long merchantId , Pageable pageable) {
        return promotionRepository.findAllByMerchantId(merchantId, pageable).map(promotionMapper::mapTo);
    }

    @Override
    public Page<PromotionDto> getAllPromotion(Pageable pageable) {
        return promotionRepository.findAll(pageable).map(promotionMapper::mapTo);
    }

    @Override
    public Page<PromotionDto> getAllValidPromotion(Pageable pageable) {
        LocalDate currentDate = LocalDate.now();
        return promotionRepository.findAllValidPromotions(currentDate , pageable).map(promotionMapper::mapTo);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        promotionRepository.delete(promotion);
    }

    @Override
    public void deletePromotion(Long promotionId) {
        promotionRepository.deleteById(promotionId);
    }
}
