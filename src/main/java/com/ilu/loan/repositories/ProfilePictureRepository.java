package com.ilu.loan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilu.loan.entities.ProfilePicture;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, String> {
}