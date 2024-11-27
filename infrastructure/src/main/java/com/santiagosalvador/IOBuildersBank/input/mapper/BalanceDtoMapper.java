package com.santiagosalvador.IOBuildersBank.input.mapper;


import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.models.BalanceDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceDtoMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "balance", target = "balance")
    BalanceDTO toDto (Balance domain);

    @InheritInverseConfiguration
    Balance toDomain (BalanceDTO dto);

}
