package com.santiagosalvador.IOBuildersBank.input.mapper;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.models.WalletDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TransactionDtoMapper.class)
public interface WalletDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source= "name", target="name")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "transactions", target = "transactions")
    WalletDTO toDto(Wallet domain);

    @InheritInverseConfiguration
    Wallet toDomain(WalletDTO dto);
}