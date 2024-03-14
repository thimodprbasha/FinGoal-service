package com.example.fingoal.service.merchantService.impl;

import com.example.fingoal.dto.MerchantDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.MerchantMapper;
import com.example.fingoal.model.merchant.Merchant;
import com.example.fingoal.model.users.User;
import com.example.fingoal.repository.MerchantRepository;
import com.example.fingoal.service.merchantService.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    private final MerchantMapper mapper;

    @Override
    public MerchantDto createMerchant(MerchantDto merchantDto , User user) {
        Merchant merchant = mapper.mapFrom(merchantDto);
        merchant.setUser(user);
        return mapper.mapTo(merchantRepository.save(merchant));

    }

    @Override
    public MerchantDto updateUserBudget(MerchantDto merchantDt) {
        return null;
    }

    @Override
    public Merchant findMerchantById(Long merchantId) {
        return merchantRepository
                .findById(merchantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Merchant with ID : \"%d\"" , merchantId )
                                , HttpStatus.NOT_FOUND
                        )
                );
    }

    @Override
    public Page<MerchantDto> getAllMerchant(Pageable pageable) {
        return merchantRepository.findAll(pageable).map(mapper::mapTo);
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
    public MerchantDto findMerchantByIdUserMapToDto(Long userId) {
        return null;
    }
}
