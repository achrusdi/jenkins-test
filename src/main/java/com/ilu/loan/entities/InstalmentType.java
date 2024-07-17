package com.ilu.loan.entities;

import java.util.List;

import com.ilu.loan.dto.response.InstalmentTypeResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "t_instalment_type")
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String instalmentType;

    @OneToMany(mappedBy = "instalmentType")
    private List<TransactionLoan> loanType;

    public InstalmentTypeResponse toResponse() {
        return InstalmentTypeResponse.builder()
                .id(id)
                .instalmentType(instalmentType)
                .build();
    }

    public InstalmentTypeResponse toResponse(InstalmentType instalmentType) {
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType())
                .build();
    }
}