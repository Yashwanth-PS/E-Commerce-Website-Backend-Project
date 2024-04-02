package com.project.EcommerceProductService.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDTO { // ErrorResponseDTO
    private HttpStatus httpStatus; // we can have custom status code
    private String message;
}