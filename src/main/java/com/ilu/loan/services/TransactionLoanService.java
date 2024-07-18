package com.ilu.loan.services;

import java.util.List;

import com.ilu.loan.entities.TransactionLoan;

public interface TransactionLoanService {
    TransactionLoan create(TransactionLoan request);

    TransactionLoan getById(String id);

    TransactionLoan update(TransactionLoan request);

    TransactionLoan updateTransactionStatus(String userId, String transactionId, String status);

    boolean delete(String id);

    List<TransactionLoan> getAll();
}