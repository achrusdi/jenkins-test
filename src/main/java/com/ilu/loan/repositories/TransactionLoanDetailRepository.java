package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.TransactionLoanDetail;

@Repository
public interface TransactionLoanDetailRepository extends JpaRepository<TransactionLoanDetail, String> {
}