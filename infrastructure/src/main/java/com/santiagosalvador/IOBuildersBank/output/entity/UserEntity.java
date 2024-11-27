package com.santiagosalvador.IOBuildersBank.output.entity;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "APP_USER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "USER_ID")
    private Long id;

    @Column(name= "USERNAME")
    private String username;

    @Column(name= "PASSWORD")
    private String password;

    @Column(name= "EMAIL")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletEntity> wallets = new ArrayList<>();


}
