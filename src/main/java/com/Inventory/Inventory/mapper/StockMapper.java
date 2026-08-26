package com.Inventory.Inventory.mapper;


import com.Inventory.Inventory.dto.StockResponse;
import com.Inventory.Inventory.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockResponse toResponse(Stock stock) {

        return new StockResponse(
                stock.getStockId(),
                stock.getProduct().getProductId(),
                stock.getProduct().getName(),
                stock.getProduct().getProductNumber(),
                stock.getLocationId(),
                stock.getLocationName(),
                stock.getQuantityOnHand(),
                stock.getAvailableQuantity(),
                stock.getQuantityBooked(),
                stock.getQuantityInPO(),
                stock.getQuantityInSO(),
                stock.getBackOrderQuantity(),
                stock.getUnitName(),
                stock.getLastAdjustmentNote(),
                stock.getCreatedAt(),
                stock.getUpdatedAt()
        );
    }
}
