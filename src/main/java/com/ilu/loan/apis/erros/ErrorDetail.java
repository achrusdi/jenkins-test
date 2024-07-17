package com.ilu.loan.apis.erros;

import lombok.Data;

@Data
public class ErrorDetail {
    private String field;
    private String issue;
}