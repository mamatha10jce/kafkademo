package com.mamatha.kafkademo.service;

import com.mamatha.kafkademo.model.Brand;
import com.mamatha.kafkademo.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
import javax.transaction.Transactional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;


    @Override
    public Page<Brand> getAllByBrandName(int page, int size, String sort) {
        return null;
    }

        @Transactional
    public Brand addBrand(Brand brand) {
        return  brandRepository.save(brand);
    }

    public Brand findById(int id) {
        return brandRepository.findById(id);
    }

    @Transactional
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }
}
