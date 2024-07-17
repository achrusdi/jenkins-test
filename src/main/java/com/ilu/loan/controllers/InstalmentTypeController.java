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
import com.ilu.loan.dto.request.InstalmentTypeRequest;
import com.ilu.loan.dto.response.InstalmentTypeResponse;
import com.ilu.loan.entities.InstalmentType;
import com.ilu.loan.services.InstalmentTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/instalment-types")
public class InstalmentTypeController {

    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    public ApiResponse<InstalmentTypeResponse> createInstalmentType(@RequestBody InstalmentTypeRequest request) {
        return ApiResponse.ok(instalmentTypeService.save(request).toResponse());
    }

    @GetMapping("/{id}")
    public ApiResponse<InstalmentTypeResponse> getInstalmentTypeByid(@PathVariable String id) {
        return ApiResponse.ok(instalmentTypeService.getById(id).toResponse());
    }

    @GetMapping
    public ApiResponse<List<InstalmentTypeResponse>> getInstalmentTypes() {
        List<InstalmentType> instalmentTypes = instalmentTypeService.getAll();
        if (instalmentTypes == null || instalmentTypes.isEmpty()) {
            return ApiResponse.ok(List.of());
        }

        return ApiResponse.ok(instalmentTypes.stream().map(InstalmentType::toResponse).toList());
    }

    @PutMapping
    public ApiResponse<InstalmentTypeResponse> updateInstalmentType(@RequestBody InstalmentTypeRequest request) {
        return ApiResponse.ok(instalmentTypeService.update(request).toResponse());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteInstalmentType(@PathVariable String id) {
        if (instalmentTypeService.delete(id)) {
            return ApiResponse.ok("OK");
        }

        return ApiResponse.ok("Not found");
    }
}