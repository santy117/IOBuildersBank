package com.santiagosalvador.IOBuildersBank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

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
