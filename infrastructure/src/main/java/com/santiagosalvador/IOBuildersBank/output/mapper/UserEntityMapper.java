package com.santiagosalvador.IOBuildersBank.output.mapper;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WalletEntityMapper.class)
public interface UserEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "wallets", target = "wallets")
    UserEntity toEntity (User domain);

    @InheritInverseConfiguration
    User toDomain (UserEntity entity);

}
