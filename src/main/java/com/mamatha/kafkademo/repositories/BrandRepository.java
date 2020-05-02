package com.mamatha.kafkademo.repositories;

import com.mamatha.kafkademo.model.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Brand findById(int id);

    Brand getAllByBrand_name(Pageable pageable);

    Brand findAllByBrand_nameIsLike(String name);

}
