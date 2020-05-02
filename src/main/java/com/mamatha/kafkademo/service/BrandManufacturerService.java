package com.mamatha.kafkademo.service;

import com.mamatha.kafkademo.model.BrandManufacturer;

import java.util.List;

public interface BrandManufacturerService {
    List<BrandManufacturer> getAllBrandManufacterer();
    BrandManufacturer addBrandManufacturer(BrandManufacturer brandManufacturer);
}
