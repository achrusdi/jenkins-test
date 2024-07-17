package com.ilu.loan.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstalmentTypeResponse {
    private String id;
    private String instalmentType;
}