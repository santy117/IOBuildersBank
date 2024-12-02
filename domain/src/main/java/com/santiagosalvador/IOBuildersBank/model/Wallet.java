package com.santiagosalvador.IOBuildersBank.model;

import com.santiagosalvador.IOBuildersBank.exception.WalletException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void transferTo(Wallet targetWallet, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new WalletException.InsufficientFundsException("Insufficient funds in wallet.");
        }

        this.withdraw(amount);
        targetWallet.deposit(amount);

        this.addTransaction(new Transaction(
                description != null ? description : "Transfer to wallet " + targetWallet.getId(),
                new Date(),
                amount.negate(),
                this.id
        ));
        targetWallet.addTransaction(new Transaction(
                description != null ? description : "Transfer from wallet " + this.id,
                new Date(),
                amount,
                targetWallet.getId()
        ));
    }
}
