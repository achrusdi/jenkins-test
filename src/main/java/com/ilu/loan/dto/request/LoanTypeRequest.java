package com.ilu.loan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ilu.loan.entities.LoanType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanTypeRequest {
    private String id;
    private String type;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double maxLoan;

    public LoanType toEntity() {
        return LoanType.builder()
                .loan_id(id)
                .type(type)
                .maximumLoan(maxLoan)
                .build();
    }
}