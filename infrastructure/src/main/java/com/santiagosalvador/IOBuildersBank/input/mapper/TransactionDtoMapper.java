package com.santiagosalvador.IOBuildersBank.input.mapper;

import com.santiagosalvador.IOBuildersBank.model.Transaction;
import com.santiagosalvador.IOBuildersBank.model.Wallet;
import com.santiagosalvador.models.TransactionDTO;
import com.santiagosalvador.models.WalletDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source="description", target="description")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "amount", target = "amount")
    TransactionDTO toDto(Transaction domain);

    @InheritInverseConfiguration
    Transaction toDomain(TransactionDTO dto);
}