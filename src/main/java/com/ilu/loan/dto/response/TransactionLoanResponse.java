package com.ilu.loan.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionLoanResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double nominal;

    private Long approvedAt;
    private String approvedBy;
    private Long rejectedAt;
    private String rejectedBy;
    private String approvalStatus;
    private List<TransactionLoanDetailResponse> transactionDetailResponses;
    private Long createdAt;
    private Long updatedAt;

    @JsonGetter("nominal")
    public String getMaxLoanAsString() {
        return new BigDecimal(String.valueOf(nominal)).toPlainString();
    }
}