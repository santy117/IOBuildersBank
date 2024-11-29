package com.santiagosalvador.IOBuildersBank.output.repository.transaction;

import com.santiagosalvador.IOBuildersBank.model.Transaction;
import com.santiagosalvador.IOBuildersBank.model.User;
import com.santiagosalvador.IOBuildersBank.output.entity.TransactionEntity;
import com.santiagosalvador.IOBuildersBank.output.entity.UserEntity;
import com.santiagosalvador.IOBuildersBank.output.mapper.TransactionEntityMapper;
import com.santiagosalvador.IOBuildersBank.output.mapper.UserEntityMapper;
import com.santiagosalvador.IOBuildersBank.output.repository.user.UserJPARepository;
import com.santiagosalvador.IOBuildersBank.repository.TransactionRepository;
import com.santiagosalvador.IOBuildersBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TransactionH2RepositoryImpl implements TransactionRepository {
    @Autowired
    TransactionJPARepository transactionJPARepository;


    @Autowired
    TransactionEntityMapper transactionEntityMapper;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity entity = transactionEntityMapper.toEntity(transaction);
        return this.transactionEntityMapper.toDomain(this.transactionJPARepository.save(entity));
    }
}
