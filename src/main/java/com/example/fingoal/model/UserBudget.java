package com.example.fingoal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_budget")
public class UserBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double incomeAmount;

    private double outcomeAmount;

    private double totalAmount;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userBudget")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
