package com.ilu.loan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trx_loan")
public class TransactionLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "approved_at")
    private Long approvedAt;

    @Column(name = "approved_by")
    private String ApprovedBy;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "nominal")
    private Double nominal;

    @Column(name = "rejected_at")
    private Long rejectedAt;

    @Column(name = "rejected_by")
    private String rejectedBy;

    @Column(name = "updated_at")
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;
}