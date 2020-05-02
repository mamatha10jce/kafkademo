package com.mamatha.kafkademo.service;

import com.mamatha.kafkademo.model.Brand;
import org.springframework.data.domain.Page;

public interface BrandService {
    Page<Brand> getAllByBrandName(int page, int size, String sort);
    Brand addBrand(Brand brand);
    Brand findById(int id);
    Brand updateBrand(Brand brand);
}
