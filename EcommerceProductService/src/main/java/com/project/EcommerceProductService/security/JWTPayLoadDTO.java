package com.project.EcommerceProductService.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTPayLoadDTO {
    private String email;
    @JsonProperty("createdAt")
    private long createdAt;
    @JsonProperty("roles")
    private List<Role> roles;
    @JsonProperty("expiryAt")
    private long expiryAt;
    @JsonProperty("userId")
    private int userId;
}
