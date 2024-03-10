package com.example.fingoal.service.merchantService;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {
    MerchantDto createMerchant(MerchantDto merchantDto);

    MerchantDto updateUserBudget(MerchantDto merchantDt);

    Merchant findMerchantById(Long merchantId);

    Page<MerchantDto> getAllMerchant(Pageable pageable);

    void deleteMerchant(Merchant merchant);

    void deleteMerchant(Long merchantId);

    MerchantDto findMerchantByIdUserMapToDto(Long userId);
}
