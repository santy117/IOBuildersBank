package com.santiagosalvador.IOBuildersBank.output.repository.wallet;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.WalletEntityMapper;
import com.santiagosalvador.IOBuildersBank.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WalletH2RepositoryImpl implements WalletRepository {

    @Autowired
    WalletJPARepository walletJPARepository;

    @Autowired
    WalletEntityMapper walletEntityMapper;

    @Override
    public Wallet findWalletByUserIdAndName(Long userId, String name) {
        Optional<WalletEntity> walletEntityOpt = walletJPARepository.findByUserIdAndName(userId, name);
        return walletEntityOpt.map(walletEntity -> walletEntityMapper.toDomain(walletEntity)).orElse(null);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        WalletEntity walletEntity = walletEntityMapper.toEntity(wallet);
        return walletEntityMapper.toDomain(walletJPARepository.save(walletEntity));
    }

    @Override
    public Wallet findWalletById(Long id) {
        Optional<WalletEntity> walletEntityOpt = walletJPARepository.findById(id);
        return walletEntityOpt.map(walletEntity -> walletEntityMapper.toDomain(walletEntity)).orElse(null);
    }
}
