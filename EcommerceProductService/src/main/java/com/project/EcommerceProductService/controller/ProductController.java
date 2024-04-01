package com.project.EcommerceProductService.controller;

import com.project.EcommerceProductService.dto.ExceptionDTO;
import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import com.project.EcommerceProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController { // Controller - Is the connection point for the outside world, 1 Controller per Entity

    /* @Autowired // Field Injection
    @Qualifier("fakeStoreProductService") // Tells the Autowired which particular implementation of the interface needs to get injected as an object
    private ProductService productService; */

    /* @Autowired // Setter Injection
    public void setProductService(ProductService productService) {
        this.productService = productService;
    } */

    private final ProductService productService; // Immutable
    /* @Autowired // Constructor Injection: Autowired for constructor injection is optional from Spring 4.3 onwards
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    } */

    @Autowired // Constructor Injection: Autowired for constructor injection is optional from Spring 4.3 onwards
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all") // localhost:8080/products/all
    public ResponseEntity<ProductListResponseDTO> getAllProducts() {
        /*
        ProductResponseDTO p1 = new ProductResponseDTO();
        p1.setId(1);
        p1.setTitle("IPhone 15 Pro");
        p1.setPrice(150000);
        p1.setImage("www.google.com/images/iphone");
        p1.setDescription("Costly Phone");
        p1.setCategory("Electronics");

        ProductResponseDTO p2 = new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook Pro");
        p2.setPrice(250000);
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Costly Laptop");
        p2.setCategory("Electronics");

        List<ProductResponseDTO> products = Arrays.asList(p1, p2);
        return ResponseEntity.ok(products);
        */
        ProductListResponseDTO response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}") // localhost:8080/products/1 --> Passing a Particular ID
    // (value = HttpHeaders.AUTHORIZATION, required = false) String authToken
    public ResponseEntity<GenericProductDTO> getProductFromId(@PathVariable("id") int id) throws ProductNotFoundException { // The id in the path variable would be injected
        GenericProductDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/title/{title}") // Passing a Particular Title
    public ResponseEntity<GenericProductDTO> getProductFromTitle(@PathVariable("title") String title) throws ProductNotFoundException { // The title in the path variable would be injected
        GenericProductDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<GenericProductDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        GenericProductDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable("id") int id) {
        boolean response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }

    public void updateProductById() {

    }

    /* @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionDTO.setMessage(productNotFoundException.getMessage());
        ResponseEntity responseEntity = new ResponseEntity(exceptionDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    } */
}

/* 3 ways of Dependency Injection :-
1) Constructor Injection
2) Field Injection
3) Setter Injection */