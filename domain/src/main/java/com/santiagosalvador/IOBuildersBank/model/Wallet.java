package com.santiagosalvador.IOBuildersBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {
    private Long id;
    private Long userId;
    private String name;
    private BigDecimal balance;
    private List<Transaction> transactions;


    public Wallet(Long userId, String name) {
        this.userId = userId;
        this.name = name;
        this.balance = BigDecimal.valueOf(0);
        this.transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("You must deposit a value greater than 0");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Not enough balance");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
}
