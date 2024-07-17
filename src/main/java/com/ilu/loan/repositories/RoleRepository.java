package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.Role;
import com.ilu.loan.entities.Role.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRole(ERole roleCustomer);
}