package com.mamatha.kafkademo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamatha.kafkademo.model.Brand;
import com.mamatha.kafkademo.model.BrandManufacturer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void postBrand(String topic, String groupId, Brand prmBrand) {
        try {
            log.info("Sending data to kafka = '{}' with topic '{}'", prmBrand.getBrand_name(), topic);
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, objectMapper.writeValueAsString(prmBrand));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void postBrandManufacturer(String topic, String groupId, BrandManufacturer brandManufacturer) {
        try {
            log.info("Sending data to kafka = '{}', with topic '{}'", brandManufacturer.getManufacturer_name(), topic);
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, objectMapper.writeValueAsString(brandManufacturer));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
