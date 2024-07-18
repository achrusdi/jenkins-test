package com.ilu.loan.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.request.LoanTypeRequest;
import com.ilu.loan.dto.response.LoanTypeResponse;
import com.ilu.loan.entities.LoanType;
import com.ilu.loan.services.LoanTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loan-types")
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @SecurityRequirement(name = "Authorization")
    @PostMapping
    public ApiResponse<LoanTypeResponse> createLoanType(@RequestBody LoanTypeRequest request) {
        LoanTypeResponse response = loanTypeService.create(request.toEntity()).toResponse();
        return ApiResponse.ok(response);
    }

    @GetMapping("/{id}")
    public ApiResponse<LoanTypeResponse> getLoanTypeByid(@PathVariable String id) {
        LoanType response = loanTypeService.getById(id);

        if (response == null) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(response.toResponse());
    }

    @GetMapping
    public ApiResponse<List<LoanTypeResponse>> getLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.getAll();
        if (loanTypes == null || loanTypes.isEmpty()) {
            return ApiResponse.ok(List.of());
        }
        return ApiResponse.ok(loanTypes.stream().map(LoanType::toResponse).toList());
    }

    @SecurityRequirement(name = "Authorization")
    @PutMapping
    public ApiResponse<LoanTypeResponse> updateLoanType(@RequestBody LoanTypeRequest request) {
        LoanType response = loanTypeService.update(request.toEntity());

        if (response == null) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(response.toResponse());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<LoanTypeResponse> deleteLoanType(@PathVariable String id) {
        if (loanTypeService.delete(id)) {
            return ApiResponse.ok(null);
        }
        return ApiResponse.ok(null);
    }
}