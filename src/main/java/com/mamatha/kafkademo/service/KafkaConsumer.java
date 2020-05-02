package com.mamatha.kafkademo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamatha.kafkademo.model.Brand;
import com.mamatha.kafkademo.model.BrandManufacturer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@Transactional
public class KafkaConsumer {

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @Autowired
    BrandService brandService;

    private Brand brandEntityFromKafka = new Brand();

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.post.brand", groupId = "inventories")
    public void processPostBrand(String brandJSON) {
        log.info("received content = '{}", brandJSON);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Brand brand = objectMapper.readValue(brandJSON, Brand.class);
            Brand brand1 = brandService.addBrand(brand);
            log.info("Success process brand '{}' with topic '{}'", brand.getBrand_name());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.put.brand", groupId = "inventories")
    public void procesPutBrand(String brandJSON){
        log.info("received content '{}'", brandJSON);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Brand brand = objectMapper.readValue(brandJSON, Brand.class);
            brandEntityFromKafka = brand;
            log.info("Success process brand '{}' with topic '{}'", brand.getBrand_name(), "inventories.kafka.put.brand" );
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Brand getBrandEntityFromKafka(int id){
        return brandEntityFromKafka;
    }

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.patch.brand", groupId = "inventories")
    public void processPatchBrand(String brandJSON){
        log.info("received content = '{}'", brandJSON);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Brand brand = objectMapper.readValue(brandJSON, Brand.class);
            Brand brand1 = brandService.updateBrand(brand);
            log.info("Success process brand '{}' with topic '{}'", brand.getBrand_name(), "inventories.kafka.patch.brand");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.post.brand.manufacturer", groupId = "inventories")
   public void processPostBrandManufacturer(String brandManufacturerJSON){
        log.info("received content = '{}'", brandManufacturerJSON);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BrandManufacturer brandManufacturer1 = objectMapper.readValue(brandManufacturerJSON, BrandManufacturer.class);
            BrandManufacturer brandManufacturer = brandManufacturerService.addBrandManufacturer(brandManufacturer1);
            log.info("success process brand manufacturer '{}' with topic '{}'", brandManufacturer.getManufacturer_name(), "inventories.kafka.post.brand");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
