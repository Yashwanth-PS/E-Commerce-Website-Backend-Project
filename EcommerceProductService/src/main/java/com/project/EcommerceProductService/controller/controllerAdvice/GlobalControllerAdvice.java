package com.project.EcommerceProductService.controller.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Global Exception Handler
public class GlobalControllerAdvice {
    // Anywhere in the code if we throw Null Pointer Exception which is not caught it will be handled
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(Exception ex){
        String exceptionResponse = "Something went wrong, error : " + ex.getMessage() + ", code : " + HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.ok(exceptionResponse);
    }
}
