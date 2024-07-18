package com.ilu.loan.entities;

import java.util.List;

import com.ilu.loan.dto.response.LoanTypeResponse;

import jakarta.persistence.Column;
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
@Builder
@Entity
@Table(name = "t_loan_type")
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String loan_id;

    @Column(name = "maximum_loan")
    private Double maximumLoan;
    private String type;

    @OneToMany(mappedBy = "loanType")
    private List<TransactionLoan> loan;

    public LoanTypeResponse toResponse() {
        return LoanTypeResponse.builder().id(this.loan_id).type(this.type).maxLoan(this.maximumLoan).build();
    }
}