package com.EcommerceUserService.controller;

import com.EcommerceUserService.dto.*;
import com.EcommerceUserService.model.Session;
import com.EcommerceUserService.model.SessionStatus;
import com.EcommerceUserService.model.User;
import com.EcommerceUserService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test-api")
    public ResponseEntity<String> testAPI() { // Test API
        return ResponseEntity.ok("Test API Response");
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO request) {
        return authService.login(request.getEmail(), request.getPassword());
    }// POST - http://localhost:8181/auth/login
/* {
    "email": "user@email.com",
    "password": "password"
} */
// Token - Response Header --> Set-Cookie

    @PostMapping("/logout/{id}")
    public ResponseEntity<Void> logout(@PathVariable("id") long userId, @RequestHeader("token") String token) {
        return authService.logout(token, userId);
    } // http://localhost:8181/auth/logout/1
    // Header - token - eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE3MDY1MjU0OTIyNDIsInJvbGVzIjpbXSwiZXhwaXJ5QXQiOjE5NzU0LCJlbWFpbCI6InVzZXJAZW1haWwuY29tIn0.7jO_ZEALPYneOaVsP6GCedcDRf1A1cJlHDLIpu6hUvI

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO request) {
        UserDTO userDTO = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    } // POST - http://localhost:8181/auth/signup
/* {
    "email": "user@email.com",
    "password": "password"
} */

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDTO request) {
        SessionStatus sessionStatus = authService.validate(request.getToken(), request.getUserId());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    } // POST - http://localhost:8181/auth/validate
/* {
    "userId": 1,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJjcmVhdGVkQXQiOjE3MDY1MjU0OTIyNDIsInJvbGVzIjpbXSwiZXhwaXJ5QXQiOjE5NzU0LCJlbWFpbCI6InVzZXJAZW1haWwuY29tIn0.7jO_ZEALPYneOaVsP6GCedcDRf1A1cJlHDLIpu6hUvI"
} */

    // Below APIs are just for testing purposes
    @GetMapping("/session")
    public ResponseEntity<List<Session>> getAllSession() {
        return authService.getAllSession();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return authService.getAllUsers();
    }
}