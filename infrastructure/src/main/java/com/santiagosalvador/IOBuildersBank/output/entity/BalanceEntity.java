package com.santiagosalvador.IOBuildersBank.output.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "BALANCE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_USER")
    private Integer id;

    @Column(name= "USERNAME")
    private String name;

    @Column(name= "BALANCE")
    private Float balance;

}
