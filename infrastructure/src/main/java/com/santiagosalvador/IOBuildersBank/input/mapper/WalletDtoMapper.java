package com.santiagosalvador.IOBuildersBank.input.mapper;

import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.IOBuildersBank.output.entity.WalletEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.models.WalletDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserDtoMapper.class)
public interface WalletDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "balance", target = "balance")
    WalletDTO toDto(Wallet domain);

    @InheritInverseConfiguration
    Wallet toDomain(WalletDTO dto);
}