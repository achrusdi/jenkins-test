package com.ilu.loan.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoanTypeResponse {
    private String id;
    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double maxLoan;

    @JsonGetter("maxLoan")
    public String getMaxLoanAsString() {
        return new BigDecimal(String.valueOf(maxLoan)).toPlainString();
    }
}