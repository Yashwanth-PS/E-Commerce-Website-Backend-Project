package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.dto.ProductResponseDTO;
import com.project.EcommerceProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder; // It will be injected Automatically

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        String getAllProductsURL = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        // Response Entity Expects the type of the class in which we want the response back - JVM cannot figure out the type on its own during runtime
        ResponseEntity<ProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductsURL, ProductResponseDTO[].class);
        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
        for (ProductResponseDTO productResponse : productResponseArray.getBody()) { // JSON will give an Array of Response
            responseDTO.getProducts().add(productResponse);
        }
        return responseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String getProductByIdURL = "https://fakestoreapi.com/products/" + id; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<ProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByIdURL, ProductResponseDTO.class);
        return productResponse.getBody(); // Rest Template returns a response Entity
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        String createProductURL = "https://fakestoreapi.com/products/"; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<ProductResponseDTO> productResponse = restTemplate.postForEntity(createProductURL, productRequestDTO, ProductResponseDTO.class);
        return productResponse.getBody(); // Rest Template returns a response Entity
    }

    @Override
    public boolean deleteProduct(int id) {
        String deleteProductURL = "https://fakestoreapi.com/products/" + id; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        restTemplate.delete(deleteProductURL);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        return null;
    }
}
