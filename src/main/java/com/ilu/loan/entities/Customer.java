package com.ilu.loan.entities;

import java.sql.Timestamp;
import java.util.List;

import com.ilu.loan.dto.response.CustomerResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "mst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<LoanDocument> loanDocument;

    @OneToMany(mappedBy = "customer")
    private List<TransactionLoan> transactionLoan;

    public CustomerResponse toResponse() {
        return new CustomerResponse(id, firstName, lastName, phone, status, dateOfBirth);
    }
}