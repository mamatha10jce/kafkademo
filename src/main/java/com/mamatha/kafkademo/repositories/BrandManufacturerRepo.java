package com.mamatha.kafkademo.repositories;

import com.mamatha.kafkademo.model.BrandManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandManufacturerRepo extends JpaRepository<BrandManufacturer, Integer> {
}
