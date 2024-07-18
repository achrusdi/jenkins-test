package com.ilu.loan.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilu.loan.entities.LoanType;
import com.ilu.loan.repositories.LoanTypeRepository;
import com.ilu.loan.services.LoanTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanType create(LoanType loanType) {
        return loanTypeRepository.save(loanType);
    }

    @Override
    public boolean delete(String id) {
        LoanType _loanType = loanTypeRepository.findById(id).orElse(null);
        if (_loanType != null) {
            loanTypeRepository.delete(_loanType);
            return true;
        }
        return false;
    }

    @Override
    public List<LoanType> getAll() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanType getById(String id) {
        return loanTypeRepository.findById(id).orElse(null);
    }

    @Override
    public LoanType update(LoanType loanType) {
        LoanType _loanType = loanTypeRepository.findById(loanType.getLoan_id()).orElse(null);
        
        if (_loanType != null) {
            return loanTypeRepository.saveAndFlush(loanType);
        }
        
        return null;
    }
    
}