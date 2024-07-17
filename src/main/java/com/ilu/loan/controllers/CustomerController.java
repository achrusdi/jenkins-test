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
import org.springframework.web.multipart.MultipartFile;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.request.CustomerRequest;
import com.ilu.loan.dto.response.CustomerResponse;
import com.ilu.loan.entities.Customer;
import com.ilu.loan.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ApiResponse<List<CustomerResponse>> getCustomers() {
        return ApiResponse.ok(customerService.getAll().stream().map(Customer::toResponse).toList());
    }

    @PutMapping
    public ApiResponse<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request) {
        return ApiResponse.ok(customerService.update(request.toEntity()).toResponse());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        return ApiResponse.ok("OK");
    }

    @PostMapping("/{customerId}/upload/avatar")
    public ApiResponse<String> uploadAvatar(@PathVariable String customerId, @RequestBody MultipartFile image) {
        return ApiResponse.ok("OK");
    }
    
}