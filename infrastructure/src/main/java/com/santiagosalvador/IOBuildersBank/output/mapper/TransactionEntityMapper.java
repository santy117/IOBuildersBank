package com.santiagosalvador.IOBuildersBank.output.mapper;


import com.santiagosalvador.IOBuildersBank.model.Transaction;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.TransactionEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WalletEntityMapper.class)
public interface TransactionEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source= "date", target="date")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "walletId", target = "wallet.id")
    TransactionEntity toEntity(Transaction domain);

    @InheritInverseConfiguration
    Transaction toDomain(TransactionEntity entity);
}
