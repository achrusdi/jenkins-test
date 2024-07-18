package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.LoanType;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
}