package com.project.EcommerceProductService.client;

import com.project.EcommerceProductService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreAPIClient { // Wrapper on FakeStoreProductAPIs
    private RestTemplateBuilder restTemplateBuilder;
    private String fakeStoreAPIURL;

    @Value("${fakestore.api.path.product}") // Field Injection
    private String fakeStoreAPIPathProduct;

    @Autowired
    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreAPIURL) { // Constructor Injection
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIURL = fakeStoreAPIURL;
    }

    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO){
        String createProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.postForEntity(createProductURL, fakeStoreProductRequestDTO, FakeStoreProductResponseDTO.class);
        return productResponse.getBody(); // Rest Template returns a response Entity
    }

    public FakeStoreProductResponseDTO getProductById(int id){
        String getProductByIdURL= fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByIdURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody(); // Rest Template returns a response Entity
    }

    public List<FakeStoreProductResponseDTO> getAllProducts(){
        String getAllProductsURL= fakeStoreAPIURL + fakeStoreAPIPathProduct; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        // Response Entity Expects the type of the class in which we want the response back - JVM cannot figure out the type on its own during runtime
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductsURL, FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody()); // Converting an Array to a List
    }

    public void deleteProduct(int id) {
        String deleteProductURL = fakeStoreAPIURL + fakeStoreAPIPathProduct + "/" + id; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        restTemplate.delete(deleteProductURL);
    }
}
