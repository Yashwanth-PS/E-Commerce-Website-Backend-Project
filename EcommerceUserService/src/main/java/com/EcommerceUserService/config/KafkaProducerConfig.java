package com.EcommerceUserService.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerConfig {
    private KafkaTemplate<String, String> kafkaTemplate; // String: Topic, String: Message

    private KafkaProducerConfig(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Will be used to push the message to the Kafka Topic
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
/* Message is String because we want to send Serialized Object over the Network to the Kafka Topic
Therefore, we have to serialize the Object to String before sending it to the Kafka Topic */