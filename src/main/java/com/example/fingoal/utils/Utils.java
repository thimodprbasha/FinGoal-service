package com.example.fingoal.utils;

import com.example.fingoal.model.Account;
import com.example.fingoal.model.Merchant;
import com.example.fingoal.model.TransactionCategory;
import com.example.fingoal.model.UserBudget;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Utils {

    public static TransactionCategory GetTransactionCategoryFromBudgetEntity(Stream<TransactionCategory> transactionCategoryStream , Long categoryId){
        return transactionCategoryStream
                .filter(element -> element.getId().equals(categoryId)).findAny().orElseThrow(RuntimeException::new);
    }

    public static Account GetAccountFromBudgetEntity(Stream<Account> accountStream , Long userId){
        return accountStream
                .filter(element -> element.getId().equals(userId)).findAny().orElseThrow(RuntimeException::new);
    }

    public static Merchant GetMerchantFromBudgetEntity(Stream<Merchant> merchantStream , Long userId){
        return merchantStream
                .filter(element -> element.getId().equals(userId)).findAny().orElseThrow(RuntimeException::new);
    }

}
