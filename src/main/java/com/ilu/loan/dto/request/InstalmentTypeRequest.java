package com.ilu.loan.dto.request;

import com.ilu.loan.entities.InstalmentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstalmentTypeRequest {
    private String id;
    private String instalmentType;

    public InstalmentType toEntity() {
        return InstalmentType.builder().id(id).instalmentType(instalmentType).build();
    }
}