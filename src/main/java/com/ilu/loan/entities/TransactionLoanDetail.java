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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trx_loan_detail")
public class TransactionLoanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_at")
    private Long createdAt;
    
    @Column(name = "loan_status")
    private String loanStatus;
    private Double nominal;

    @Column(name = "transaction_date")
    private Long transactionDate;

    @Column(name = "updated_at")
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "guarantee_picture_id")
    private GuaranteePicture guaranteePicture;

    @ManyToOne
    @JoinColumn(name = "trx_loan_id")
    private TransactionLoan transactionLoan;

}