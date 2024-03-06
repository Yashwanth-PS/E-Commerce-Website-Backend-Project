package com.project.EcommerceProductService.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean // By Default the bean of RestTemplate is created by Spring when we run the application
    @LoadBalanced // LoadBalanced RestTemplate
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}