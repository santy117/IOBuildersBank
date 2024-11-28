package com.santiagosalvador.IOBuildersBank.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private Long id;
    private String description;
    private Date date;
    private BigDecimal amount;
    private Wallet wallet;
}
