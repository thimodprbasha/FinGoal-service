package com.example.fingoal.utils;

import com.example.fingoal.model.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public static Map<String , Object> ResponseBody(HttpStatusCode status , String message){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("error", message);

        return responseBody;
    }

    public static <E> Map<String , Object> ResponseBody(HttpStatusCode status , List<E> messages){
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", status.value());
        responseBody.put("error", messages);

        return responseBody;
    }

    public User DowncastFromUserDetailsToUser(UserDetails userDetails){
        return (User) userDetails;
    }

}
