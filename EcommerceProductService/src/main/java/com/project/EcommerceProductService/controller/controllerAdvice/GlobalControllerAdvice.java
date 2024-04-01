package com.project.EcommerceProductService.controller.controllerAdvice;

import com.project.EcommerceProductService.dto.ExceptionDTO;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // Global Exception Handler
public class GlobalControllerAdvice {
    // Anywhere in the code if we throw Null Pointer Exception which is not caught it will be handled
    @ExceptionHandler(value = ProductNotFoundException.class)
    /* @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestBody() */
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    /* @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    private ResponseEntity<ExceptionDTO> handleArrayIndexOutOfBoundException(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        return null;
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ExceptionDTO> handleNullPointerException(NullPointerException nullPointerException) {
        return null;
    } */
}
