package com.EcommerceUserService.service;

import com.EcommerceUserService.dto.UserDTO;
import com.EcommerceUserService.model.Session;
import com.EcommerceUserService.model.SessionStatus;
import com.EcommerceUserService.model.User;
import com.EcommerceUserService.repository.SessionRepository;
import com.EcommerceUserService.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    public ResponseEntity<UserDTO> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            return null;
        }
        String token = RandomStringUtils.randomAlphanumeric(30);
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);
        UserDTO userDto = new UserDTO();
        // Map<String, String> headers = new HashMap<>();
        // headers.put(HttpHeaders.SET_COOKIE, token);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
        // response.getHeaders().add(HttpHeaders.SET_COOKIE, token);
        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty()) {
            return null;
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    public UserDTO signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User savedUser = userRepository.save(user);
        return UserDTO.from(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty()) {
            return null;
        }
        return SessionStatus.ACTIVE;
    }
}