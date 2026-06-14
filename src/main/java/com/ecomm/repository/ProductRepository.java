package com.ecomm.repository;

import com.ecomm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Used to guard against deleting categories that still have products
    long countByCategoryCategoryId(Long categoryId);
}
