package com.example.fingoal.service.merchantService;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {
    MerchantDto createMerchant(MerchantDto merchantDto , User user);

    MerchantDto updateMerchant(Long merchantId , MerchantDto merchantDt);

    Merchant findMerchantById(Long merchantId);

    Page<MerchantDto> getAllMerchant(Pageable pageable);

    void deleteMerchant(Merchant merchant);

    void deleteMerchant(Long merchantId);

    MerchantDto findMerchantByIdMapToDto(Long merchantId);

    Page<MerchantDto> findAllMerchantByUser(Long userId , Pageable pageable);
}
