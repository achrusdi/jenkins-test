package com.ilu.loan.services;

import java.util.List;

import com.ilu.loan.entities.LoanType;

public interface LoanTypeService {
    LoanType create(LoanType loanType);

    LoanType getById(String id);

    LoanType update(LoanType loanType);

    boolean delete(String id);

    List<LoanType> getAll();
}