package com.santiagosalvador.IOBuildersBank.repository;

import com.santiagosalvador.IOBuildersBank.model.Transaction;

public interface TransactionRepository {

    Transaction saveTransaction(Transaction transaction);
}
