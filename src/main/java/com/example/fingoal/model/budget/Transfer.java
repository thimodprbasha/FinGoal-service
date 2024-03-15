package com.example.fingoal.model.budget;

import com.example.fingoal.model.customer.Account;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "transfers")
public class Transfer extends Transaction {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id" , referencedColumnName = "id")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id" , referencedColumnName = "id")
    private Account toAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_budget_id" , referencedColumnName = "id")
    private UserBudget userBudget;




}
