package com.Inventory.Inventory.repository;

import com.Inventory.Inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    boolean existsByProductNumber(String productNumber);
    Optional<Product> findByProductNumber(String productNumber);
}
