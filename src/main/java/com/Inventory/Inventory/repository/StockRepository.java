package com.Inventory.Inventory.repository;




import com.Inventory.Inventory.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository
        extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductProductIdAndLocationId(
            Long productId,
            Long locationId
    );

    List<Stock> findByProductProductId(Long productId);

    boolean existsByProductProductIdAndLocationId(
            Long productId,
            Long locationId
    );
}
