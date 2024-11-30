package com.santiagosalvador.IOBuildersBank.input.mapper;

import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.mapper.WalletEntityMapper;
import com.santiagosalvador.models.UserCreationDTO;
import com.santiagosalvador.models.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WalletDtoMapper.class)
public interface UserDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "wallets", target = "wallets")
    UserDTO toDto (User domain);

    @InheritInverseConfiguration
    User toDomain (UserDTO dto);

}
