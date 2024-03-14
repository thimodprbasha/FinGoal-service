package com.example.fingoal.service.budgetService.impl;

import com.example.fingoal.dto.RequestUserBudgetDto;
import com.example.fingoal.dto.TransactionDto;
import com.example.fingoal.dto.ResponseUserBudgetDto;
import com.example.fingoal.exception.ResourceNotFoundException;
import com.example.fingoal.mappers.impl.BudgetMapper;
import com.example.fingoal.mappers.impl.IncomeMapper;
import com.example.fingoal.mappers.impl.OutcomeMapper;
import com.example.fingoal.model.budget.IncomeTransaction;
import com.example.fingoal.model.budget.OutcomeTransaction;
import com.example.fingoal.model.users.User;
import com.example.fingoal.model.budget.UserBudget;
import com.example.fingoal.repository.UserBudgetRepository;
import com.example.fingoal.service.budgetService.BudgetService;
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
public class BudgetServiceImpl implements BudgetService {

    private final UserBudgetRepository userBudgetRepository;

    private final BudgetMapper mapper;

    private final IncomeMapper incomeMapper;

    private final OutcomeMapper outcomeMapper;

    @Override
    public ResponseUserBudgetDto createBudget(ResponseUserBudgetDto responseUserBudgetDto, User user) {
        UserBudget userBudget = mapper.mapFrom(responseUserBudgetDto);
        userBudget.setUser(user);
        UserBudget saved = userBudgetRepository.save(userBudget);
        return mapper.mapTo(saved);
    }
    @Override
    @Transactional
    public ResponseUserBudgetDto updateUserBudget(Long budgetId , RequestUserBudgetDto requestUserBudgetDto){
        UserBudget userBudget = this.findUserBudgetByBudget(budgetId);
        Optional.ofNullable(requestUserBudgetDto.getBudgetName()).ifPresent(userBudget::setBudgetName);
        Optional.ofNullable(requestUserBudgetDto.getBudgetAmount()).ifPresent(userBudget::setBudgetAmount);
        Optional.ofNullable(requestUserBudgetDto.getStartDate()).ifPresent(date -> {
           if (LocalDate.parse(date.toString()).isBefore(LocalDate.now())){
               //TODO fix this custom exception
               throw new IllegalArgumentException("Date cant be past");
            }
           userBudget.setStartDate(userBudget.getStartDate());
        });
        return null;
    }

    @Override
    public void deleteBudget(UserBudget userBudget) {
        userBudgetRepository.delete(userBudget);
    }

    @Override
    public void deleteBudget(Long budgetId) {
        userBudgetRepository.deleteById(budgetId);
    }

    @Override
    public UserBudget findUserBudgetByUser(Long userId){
        return userBudgetRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Budget with UserID : %d  or Budget not have been created" , userId )
                                , HttpStatus.NOT_FOUND)
                );

    }

    @Override
    public Page<TransactionDto> findAllIncomeAndOutcomeTransactions(Long budgetId , Pageable pageable) {
        return userBudgetRepository
                .findIncomeAndOutcomeTransactionsByUserBudgetId(budgetId , pageable)
                .map(transaction -> {
                    if (transaction instanceof IncomeTransaction){
                        return incomeMapper.mapTo((IncomeTransaction) transaction);
                    } else if (transaction instanceof OutcomeTransaction) {
                        return outcomeMapper.mapTo((OutcomeTransaction) transaction);
                    }
                    return null;
        });
    }

    @Override
    public ResponseUserBudgetDto findUserBudgetByUserMapToDto(Long userId) {
        return mapper.mapTo(this.findUserBudgetByUser(userId));
    }

    @Override
    public ResponseUserBudgetDto findUserBudgetByBudgetMapToDto(Long budgetId) {
        return mapper.mapTo(this.findUserBudgetByBudget(budgetId));
    }

    @Override
    public UserBudget findUserBudgetByBudget(Long budgetId){
        return userBudgetRepository
                .findById(budgetId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Could not find a Budget with ID : %d  or Budget not have been created" , budgetId )
                                , HttpStatus.FORBIDDEN)
                );

    }
    @Override
    public boolean isUserBudgetExistsOnUser(UserBudget userBudget){
       if (Optional.ofNullable(userBudget).isPresent()){
           return true;
       }else {
           throw  new ResourceNotFoundException(
                   "Budget not have been created"
                   , HttpStatus.FORBIDDEN);
       }
    }

    @Override
    public void isUserBudgetExistsOnConnectedUser(UserBudget userBudget){
        Optional.ofNullable(userBudget)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Budget not have been created"
                                , HttpStatus.FORBIDDEN)
                );
    }

}
