package com.ilu.loan.apis.erros;

import java.util.List;

import lombok.Data;

@Data
public class ApiError {
    private int code;
    private String message;
    private List<String> details;
}