package com.project.EcommerceProductService.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private int messageCode; // we can have custom status code
}