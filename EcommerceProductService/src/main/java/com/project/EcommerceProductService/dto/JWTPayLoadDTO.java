package com.project.EcommerceProductService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTPayLoadDTO {
    @JsonProperty("createdAt")
    private long createdAt;
    @JsonProperty("roles")
    private String[] roles;
    @JsonProperty("expiryAt")
    private long expiryAt;
    @JsonProperty("userId")
    private int userId;
}
