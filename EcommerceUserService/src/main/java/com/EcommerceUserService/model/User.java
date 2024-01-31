package com.EcommerceUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "ECOM_User")
@Getter
@Setter
public class User extends BaseModel {
    private String email;
    private String password;
    @ManyToMany // User:Role -> M:M
    private Set<Role> roles = new HashSet<>(); // Set ensures there are no duplicates
}
