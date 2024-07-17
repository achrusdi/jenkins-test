package com.ilu.loan.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilu.loan.dto.request.RoleRequest;
import com.ilu.loan.entities.Role;
import com.ilu.loan.repositories.RoleRepository;
import com.ilu.loan.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role create(RoleRequest request) {
        Role role = new Role();
        role.setRole(Role.ERole.valueOf(request.getRole()));

        return roleRepository.save(role);
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Role> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Role update(RoleRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}