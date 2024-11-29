package com.santiagosalvador.IOBuildersBank.output.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name= "DATE")
    private Date date;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "WALLET_ID", nullable = false)
    private WalletEntity wallet;


}
