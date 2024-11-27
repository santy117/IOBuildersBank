package com.santiagosalvador.IOBuildersBank.input.mapper;

import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.models.BalanceDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T16:38:24+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BalanceDtoMapperImpl implements BalanceDtoMapper {

    @Override
    public BalanceDTO toDto(Balance domain) {
        if ( domain == null ) {
            return null;
        }

        BalanceDTO balanceDTO = new BalanceDTO();

        balanceDTO.setName( domain.getName() );
        balanceDTO.setId( domain.getId() );
        balanceDTO.setBalance( domain.getBalance() );

        return balanceDTO;
    }

    @Override
    public Balance toDomain(BalanceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Balance balance = new Balance();

        balance.setName( dto.getName() );
        balance.setId( dto.getId() );
        balance.setBalance( dto.getBalance() );

        return balance;
    }
}
