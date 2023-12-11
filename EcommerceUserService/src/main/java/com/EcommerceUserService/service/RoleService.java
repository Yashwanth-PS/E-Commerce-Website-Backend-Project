package com.EcommerceUserService.service;

import com.EcommerceUserService.model.Role;
import com.EcommerceUserService.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) { // Constructor Injection - Autowired is Optional
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name) {
        Role role = new Role();
        role.setRole(name);
        return roleRepository.save(role);
    }
}