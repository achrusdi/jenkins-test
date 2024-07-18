package com.ilu.loan.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.request.TransactionLoanRequest;
import com.ilu.loan.dto.response.FileResponse;
import com.ilu.loan.dto.response.TransactionLoanResponse;
import com.ilu.loan.entities.Customer;
import com.ilu.loan.entities.InstalmentType;
import com.ilu.loan.entities.LoanType;
import com.ilu.loan.entities.TransactionLoan;
import com.ilu.loan.services.CustomerService;
import com.ilu.loan.services.FileService;
import com.ilu.loan.services.InstalmentTypeService;
import com.ilu.loan.services.LoanTypeService;
import com.ilu.loan.services.TransactionLoanService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionLoanController {

    private final TransactionLoanService transactionLoanService;
    private final LoanTypeService loanTypeService;
    private final InstalmentTypeService instalmentTypeService;
    private final CustomerService customerService;
    private final FileService fileService;

    @SecurityRequirement(name = "Authorization")
    @PostMapping
    public ApiResponse<TransactionLoanResponse> createTransaction(@RequestBody TransactionLoanRequest request) {
        LoanType loanType = loanTypeService.getById(request.getLoanType().getId());

        InstalmentType instalmentType = instalmentTypeService.getById(request.getInstalmentType().getId());

        Customer customer = customerService.getById(request.getCustomer().getId());

        if (loanType == null || instalmentType == null || customer == null) {
            return ApiResponse.ok(null);
        }

        TransactionLoan transactionLoan = new TransactionLoan();

        transactionLoan.setLoanType(loanType);
        transactionLoan.setInstalmentType(instalmentType);
        transactionLoan.setCustomer(customer);
        transactionLoan.setNominal(request.getNominal());

        transactionLoan = transactionLoanService.create(transactionLoan);

        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }

        return ApiResponse.ok(transactionLoan.toResponse());
    }

    @GetMapping("/{id}")
    public ApiResponse<TransactionLoanResponse> getTransactionById(@PathVariable String id) {
        TransactionLoan transactionLoan = transactionLoanService.getById(id);
        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(transactionLoan.toResponse());
    }

    @PutMapping("/{trxId}/pay")
    public ApiResponse<FileResponse> updateTransaction(@RequestBody MultipartFile file, @PathVariable String trxId) throws IOException {
        FileResponse response = fileService.storeFile(file, "transactions", trxId);

        if (response == null) {
            return ApiResponse.ok(null);
        }

        return ApiResponse.ok(response);
    }

    @SecurityRequirement(name = "Authorization")
    @PutMapping("/{userId}/approve")
    public ApiResponse<TransactionLoanResponse> approveTransaction(@PathVariable String userId, @RequestBody TransactionLoanRequest request) {

        TransactionLoan transactionLoan = transactionLoanService.getById(request.getId());

        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }

        transactionLoan = transactionLoanService.updateTransactionStatus(userId, transactionLoan.getId(), "APPROVED");

        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(transactionLoan.toResponse());
    }

    @SecurityRequirement(name = "Authorization")
    @PutMapping("/{userId}/reject")
    public ApiResponse<TransactionLoanResponse> rejectTransaction(@PathVariable String userId, @RequestBody TransactionLoanRequest request) {
        TransactionLoan transactionLoan = transactionLoanService.getById(request.getId());
        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }
        transactionLoan = transactionLoanService.updateTransactionStatus(userId, transactionLoan.getId(), "REJECTED");
        if (transactionLoan == null) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(transactionLoan.toResponse());
    }
}