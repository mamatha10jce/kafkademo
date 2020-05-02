package com.mamatha.kafkademo.controller;

import com.mamatha.kafkademo.model.Brand;
import com.mamatha.kafkademo.repositories.BrandRepository;
import com.mamatha.kafkademo.service.BrandService;
import com.mamatha.kafkademo.service.KafkaConsumer;
import com.mamatha.kafkademo.service.KafkaProducer;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.MulticastChannel;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping(value = "/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.brand}")
    String postBrandTopic;

    @Value("${inventories.kafka.put.brand}")
    String putBrandTopic;

    @Value("${inventories.kafka.patch.brand}")
    String patchBrandTopic;

    //Get all brands with page and size
    @GetMapping(value = "")
    public ResponseEntity<?> getAllBrandName(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sort",
            defaultValue = "brandname,asc") String sort,
            PagedResourcesAssembler pagedResourcesAssembler,
            @RequestHeader("User-Agent") String userAgent
    ){
        log.info("fetching all brands");
        Page<Brand> brand = null;
        try{
            brand = brandService.getAllByBrandName(page, size, sort);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put(HttpHeaders.USER_AGENT, Arrays.asList(userAgent));
       // PagedResources<MultiResource> pagedResources = pagedResourcesAssembler.toResource(brand);
        //return new ResponseEntity<PagedResources>(pagedResources, headers, HttpStatus.OK);
        return null;
    }
}
