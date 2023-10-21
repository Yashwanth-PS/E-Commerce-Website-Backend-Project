package com.project.EcommerceProductService.controller;

import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductResponseDTO;
import com.project.EcommerceProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("fakeStoreProductService") // Tells the Autowired which particular implementation of the interface needs to get injected as an object
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity getAllProducts(){
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

    @GetMapping("/product/1")
    public ResponseEntity getProductFromId(){
        ProductResponseDTO response = productService.getProductById(1);
        return ResponseEntity.ok(response);
    }
}
