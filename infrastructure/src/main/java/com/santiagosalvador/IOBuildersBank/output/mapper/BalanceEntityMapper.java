package com.santiagosalvador.IOBuildersBank.output.mapper;

import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.IOBuildersBank.output.entity.BalanceEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceEntityMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "balance", target = "balance")
    BalanceEntity toEntity (Balance domain);

    @InheritInverseConfiguration
    Balance toDomain (BalanceEntity entity);

}
