package com.santiagosalvador.IOBuildersBank.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Balance {

    private String name;
    private Integer id;
    private Float balance;
}
