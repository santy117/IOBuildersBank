package com.santiagosalvador.IOBuildersBank.output.mapper;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserEntityMapper.class)
public interface WalletEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source="name", target="name")
    @Mapping(source = "balance", target = "balance")
    WalletEntity toEntity(Wallet domain);

    @InheritInverseConfiguration
    Wallet toDomain(WalletEntity entity);
}
