package com.ilu.loan.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ilu.loan.entities.User;

public interface UserService extends UserDetailsService {
    List<User> getAll();

    User getById(String id);

    User create(User request);

    User update(User request);

    boolean delete(String id);

}