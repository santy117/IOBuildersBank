package com.santiagosalvador.IOBuildersBank.output.mapper;

import com.santiagosalvador.IOBuildersBank.model.Balance;
import com.santiagosalvador.IOBuildersBank.output.entity.BalanceEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.BalanceEntity.BalanceEntityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T16:38:24+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BalanceEntityMapperImpl implements BalanceEntityMapper {

    @Override
    public BalanceEntity toEntity(Balance domain) {
        if ( domain == null ) {
            return null;
        }

        BalanceEntityBuilder balanceEntity = BalanceEntity.builder();

        balanceEntity.name( domain.getName() );
        balanceEntity.id( domain.getId() );
        balanceEntity.balance( domain.getBalance() );

        return balanceEntity.build();
    }

    @Override
    public Balance toDomain(BalanceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Balance balance = new Balance();

        balance.setName( entity.getName() );
        balance.setId( entity.getId() );
        balance.setBalance( entity.getBalance() );

        return balance;
    }
}
