package com.EcommerceUserService.service;

import com.EcommerceUserService.model.Role;
import com.EcommerceUserService.model.User;
import com.EcommerceUserService.repository.RoleRepository;
import com.EcommerceUserService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserDetails(Long userId) {
        // Optional is used because the User might not be present - Handle Null and NullPointerException
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return null;
        }

        return UserDTO.from(userOptional.get());
    }

    public UserDTO setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        Set<Role> roles = roleRepository.findAllByIdIn(roleIds);

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return UserDTO.from(savedUser);
    }
}
