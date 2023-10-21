package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductResponseDTO;
import com.project.EcommerceProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder; // It will be injected Automatically

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public ProductListResponseDTO getAllProducts() {
        String getAllProductsURL = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        /* ResponseEntity<ProductListResponseDTO> productResponse = restTemplate.getForEntity(getAllProductsURL, ProductListResponseDTO.class);
        return productResponse.getBody(); */
        return null;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String getProductByIdURL = "https://fakestoreapi.com/products/" + id; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<ProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByIdURL, ProductResponseDTO.class);
        return productResponse.getBody(); // Rest Template returns a response Entity
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        return null;
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        return null;
    }
}
