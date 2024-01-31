package com.EcommerceUserService.service;

import com.EcommerceUserService.dto.UserDTO;
import com.EcommerceUserService.exception.InvalidCredentialException;
import com.EcommerceUserService.exception.InvalidTokenException;
import com.EcommerceUserService.exception.UserNotFoundException;
import com.EcommerceUserService.mapper.UserEntityDTOMaapper;
import com.EcommerceUserService.model.Session;
import com.EcommerceUserService.model.SessionStatus;
import com.EcommerceUserService.model.User;
import com.EcommerceUserService.repository.SessionRepository;
import com.EcommerceUserService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<List<Session>> getAllSession() {
        List<Session> sessions = sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserDTO> login(String email, String password) {
        // Get User Details from DB
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User for the given Email ID does not exist");
        }

        // Verify the user password given at the time of login
        User user = userOptional.get();
        // if (!user.getPassword().equals(password)) {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) { // Password Comparison
            throw new InvalidCredentialException("Invalid Credentials");
        }

        // Token Generation, RandomStringUtils - Package in Java which helps to generate Random Data (Random String, Random Number)
        // String token = RandomStringUtils.randomAlphanumeric(30);

        // Token Generation - Based on JWT
        // Header
        MacAlgorithm algorithm = Jwts.SIG.HS256; // Hashing Algorithm for JWT Token
        // Signature
        SecretKey secretKey = algorithm.key().build(); // Generating the Secret Key
        // Payload -> Claims - Attributes for JWT Token
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.getId()); // ("email", user.getEmail()) -> Vulnerable
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));
        String token = Jwts.builder()
                .claims(jsonForJWT) // Added the Claims
                .signWith(secretKey, algorithm) // Added the Algorithm and Key
                .compact(); // Building the Token

        // Session Creation
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        session.setLoginAt(new Date());
        sessionRepository.save(session);

        // Generating the Response
        UserDTO userDTO = UserEntityDTOMaapper.getUserDTOFromUserEntity(user);

        // Setting up the Headers
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token); // SET_COOKIE - It's a constant String
        // headers.add(HttpHeaders.ACCEPT, List.of("application/json", "text", "image").toString());
        /* MultiValueMapAdapter is a Map with single Key and multiple Values
        Headers:
               Key      :      Value
              Cookie    :    auth-token
           Content-Type : application.json
        Accept-Encoding : gzip, deflate,br */

        // Set and return the Response
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDTO, headers, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        // Validations -> Token Exists, Token is not Expired, User Exists, else Throw an Exception
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty()) {
            return null; // TODO: Throw an exception here
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    public UserDTO signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // The password would be encoded/encrypted while saving to the DB
        User savedUser = userRepository.save(user);
        return UserEntityDTOMaapper.getUserDTOFromUserEntity(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        // TODO: Check Expiry
        // JWT Parser --> Parse the encoded JWT Token to read the Claims
        // Verifying from DB if session exists
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        // Verify if session is active or not
        if (sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus().equals(SessionStatus.ENDED)) {
            throw new InvalidTokenException("Token is Invalid");
        }
        return SessionStatus.ACTIVE;
    }
}