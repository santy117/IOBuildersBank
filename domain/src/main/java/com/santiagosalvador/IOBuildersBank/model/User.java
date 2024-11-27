package com.santiagosalvador.IOBuildersBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Wallet> wallets;

    public void addWallet(Wallet wallet) {
        if (wallets.contains(wallet)) {
            throw new IllegalArgumentException("This user already contains the wallet");
        }
        wallets.add(wallet);
    }

    public void removeWallet(Wallet wallet) {
        if (!wallets.contains(wallet)) {
            throw new IllegalArgumentException("This user does not contain the wallet");
        }
        wallets.remove(wallet);
    }


}
