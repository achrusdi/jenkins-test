package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.GuaranteePicture;

@Repository
public interface GuaranteePictureRepository extends JpaRepository<GuaranteePicture, String> {
}