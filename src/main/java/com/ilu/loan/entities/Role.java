package com.ilu.loan.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ilu.loan.dto.response.RoleResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String role_id;

    @Column(name = "role", nullable = false, unique = true)
    private ERole role;

    @JsonIgnore
    @JsonBackReference
    @JsonManagedReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public static enum ERole {
        ROLE_CUSTOMER,
        ROLE_STAFF,
        ROLE_ADMIN;
    }

    public RoleResponse toResponse() {
        return RoleResponse.builder().id(role_id).role(role.name()).build();
    }
}