package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.InstalmentType;

@Repository
public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String>{
}