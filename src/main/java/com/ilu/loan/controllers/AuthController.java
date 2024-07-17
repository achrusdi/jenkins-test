package com.ilu.loan.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.request.AuthRequest;
import com.ilu.loan.dto.response.AuthResponse;
import com.ilu.loan.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("signup")
    public ApiResponse<AuthResponse> register(@RequestBody @Valid AuthRequest request) {
        return ApiResponse.ok(authService.register(request));
    }

    @PostMapping("signin")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        return ApiResponse.ok(authService.login(request));
    }
}