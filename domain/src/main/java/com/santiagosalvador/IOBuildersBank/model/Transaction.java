package com.santiagosalvador.IOBuildersBank.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private Long id;
    private String description;
    private Date date;
    private BigDecimal amount;
    private Long walletId;

    public Transaction(String description, Date date, BigDecimal amount, Long walletId) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.walletId = walletId;
    }
}
