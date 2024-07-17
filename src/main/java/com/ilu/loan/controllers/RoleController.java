package com.ilu.loan.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilu.loan.apis.responses.ApiResponse;
import com.ilu.loan.dto.request.RoleRequest;
import com.ilu.loan.dto.response.RoleResponse;
import com.ilu.loan.entities.Role;
import com.ilu.loan.services.RoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        List<Role> roles = roleService.getAll();

        if (roles == null || roles.isEmpty()) {
            return ApiResponse.ok(List.of());
        }
        
        return ApiResponse.ok(roles.stream().map(role -> role.toResponse()).toList());
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        Role role = roleService.create(request);

        if (role == null) {
            // return ApiResponse.error("Failed to create role");
            return null;
        }

        return ApiResponse.ok(role.toResponse());
    }
}