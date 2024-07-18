package com.ilu.loan.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilu.loan.entities.TransactionLoan;
import com.ilu.loan.entities.TransactionLoanDetail;
import com.ilu.loan.entities.User;
import com.ilu.loan.repositories.TransactionLoanDetailRepository;
import com.ilu.loan.repositories.TransactionLoanRepository;
import com.ilu.loan.services.TransactionLoanService;
import com.ilu.loan.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionLoanServiceImpl implements TransactionLoanService {

    private final TransactionLoanRepository transactionLoanRepository;
    private final TransactionLoanDetailRepository transactionLoanDetailRepository;
    private final UserService userService;

    @Override
    public TransactionLoan create(TransactionLoan request) {
        request.setCreatedAt(System.currentTimeMillis());

        return transactionLoanRepository.save(request);
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<TransactionLoan> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransactionLoan getById(String id) {
        return transactionLoanRepository.findById(id).orElse(null);
    }

    @Override
    public TransactionLoan update(TransactionLoan request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransactionLoan updateTransactionStatus(String userId, String transactionId, String status) {

        User user = userService.getById(userId);

        if (user == null) {
            return null;
        }

        TransactionLoan transactionLoan = transactionLoanRepository.findById(transactionId).orElse(null);

        if (transactionLoan == null) {
            return null;
        }

        List<TransactionLoanDetail> transactionLoanDetail = transactionLoan.getTransactionLoanDetail();

        if (transactionLoanDetail == null) {
            return null;
        }

        for (TransactionLoanDetail detail : transactionLoanDetail) {
            detail.setLoanStatus("PAID");

            transactionLoanDetailRepository.save(detail);
        }

        if (status.equals("REJECTED")) {
            transactionLoan.setApprovalStatus("REJECTED");
            transactionLoan.setRejectedAt(System.currentTimeMillis());
            transactionLoan.setRejectedBy(user.getUser_id());

            transactionLoan.setApprovedAt(null);
            transactionLoan.setApprovedBy(null);
        }

        if (status.equals("APPROVED")) {
            transactionLoan.setApprovalStatus("APPROVED");
            transactionLoan.setApprovedAt(System.currentTimeMillis());
            transactionLoan.setApprovedBy(user.getUser_id());

            transactionLoan.setRejectedAt(null);
            transactionLoan.setRejectedBy(null);
        }

        transactionLoan.setUpdatedAt(System.currentTimeMillis());

        transactionLoanRepository.save(transactionLoan);

        return transactionLoan;
    }
    
}