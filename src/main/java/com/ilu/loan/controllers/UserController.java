package com.ilu.loan.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.response.UserResponse;
import com.ilu.loan.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable String id) {
        return ApiResponse.ok(userService.getById(id).toResponse());
    }
}