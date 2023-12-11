package com.EcommerceUserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {
    private String token;
    private Long userId;
}