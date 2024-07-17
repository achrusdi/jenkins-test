package com.ilu.loan.services;

import java.util.List;

import com.ilu.loan.dto.request.RoleRequest;
import com.ilu.loan.entities.Role;

public interface RoleService {

    List<Role> getAll();

    Role getById(String id);

    Role create(RoleRequest request);

    Role update(RoleRequest request);

    boolean delete(String id);
}