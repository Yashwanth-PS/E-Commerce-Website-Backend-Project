package com.EcommerceUserService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, String> getKafkaTemplate(ProducerFactory producerFactory) {
        return new KafkaTemplate<String, String>(producerFactory);
    }
}