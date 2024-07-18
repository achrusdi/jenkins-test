package com.ilu.loan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionLoanDetailResponse {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private String loanStatus;
    private Long createdAt;
    private Long updatedAt;
}