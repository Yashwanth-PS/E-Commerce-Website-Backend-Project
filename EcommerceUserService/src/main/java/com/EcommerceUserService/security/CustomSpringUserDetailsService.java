package com.EcommerceUserService.security;

import com.EcommerceUserService.model.User;
import com.EcommerceUserService.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomSpringUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("User Details with the given User Name is Not Found");
        }
        User user = userOptional.get();
        return new CustomSpringUserDetails(user);
    }
}
