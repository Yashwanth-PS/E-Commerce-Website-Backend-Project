package com.EcommerceUserService.controller;

import com.EcommerceUserService.dto.SetUserRolesRequestDTO;
import com.EcommerceUserService.dto.UserDTO;
import com.EcommerceUserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") Long userId) {
        UserDTO userDTO = userService.getUserDetails(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDTO request) {
        UserDTO userDTO = userService.setUserRoles(userId, request.getRoleIds());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}