package com.mamatha.kafkademo.service;

import com.mamatha.kafkademo.model.BrandManufacturer;
import com.mamatha.kafkademo.repositories.BrandManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManufacturersServiceImpl implements BrandManufacturerService {

    @Autowired
    BrandManufacturerRepo brandManufacturerRepo;

    @Override
    public List<BrandManufacturer> getAllBrandManufacterer() {
        return brandManufacturerRepo.findAll();
    }

    @Override
    public BrandManufacturer addBrandManufacturer(BrandManufacturer brandManufacturer) {
        return brandManufacturerRepo.save(brandManufacturer);
    }
}
