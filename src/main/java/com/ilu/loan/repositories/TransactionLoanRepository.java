package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.TransactionLoan;

@Repository
public interface TransactionLoanRepository extends JpaRepository<TransactionLoan, String> {
}