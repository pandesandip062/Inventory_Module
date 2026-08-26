package com.Inventory.Inventory.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockResponse(

        Long stockId,

        Long productId,

        String productName,

        String productNumber,

        Long locationId,

        String locationName,

        BigDecimal quantityOnHand,

        BigDecimal availableQuantity,

        BigDecimal quantityBooked,

        BigDecimal quantityInPO,

        BigDecimal quantityInSO,

        BigDecimal backOrderQuantity,

        String unitName,

        String lastAdjustmentNote,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
