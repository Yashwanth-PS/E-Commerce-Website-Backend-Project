package com.project.EcommerceProductService.client;

import com.project.EcommerceProductService.dto.ValidateTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component // Client should not have business logic
public class UserServiceClient { // Wrapper on UserServiceAPIs
    private RestTemplateBuilder restTemplateBuilder;
    private String userServiceAPIURL; // In future the Base URL can change, Post Deployment

    @Value("${userservice.api.path.validate}") // Field Injection
    private String userServiceValidatePath;

    @Autowired
    public UserServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${userservice.api.url}") String userServiceAPIURL) { // Constructor Injection
        this.restTemplateBuilder = restTemplateBuilder;
        this.userServiceAPIURL = userServiceAPIURL;
    }

    public String validateToken(ValidateTokenDTO validateTokenDTO){
        String validateTokenURL = userServiceAPIURL + userServiceValidatePath; // In Production we should create a class of constants
        RestTemplate restTemplate = restTemplateBuilder.build(); // To get the object
        ResponseEntity<String> validateTokenResponse = restTemplate.postForEntity(validateTokenURL, validateTokenDTO, String.class);
        return validateTokenResponse.getBody(); // Rest Template returns a response Entity
    }
}
