package com.Inventory.Inventory.dto;



import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateStockRequest(

        @NotNull(message = "Product ID is required")
        Long productId,

        @NotNull(message = "Location ID is required")
        Long locationId,

        @NotBlank(message = "Location name is required")
        String locationName,

        @NotNull(message = "Adjusted quantity is required")
        @Positive(message = "Adjusted quantity must be greater than zero")
        BigDecimal adjustedQty,

        @NotBlank(message = "Base unit name is required")
        String baseUnitName,

        String transactionUnitName,

        @NotNull
        @Positive
        BigDecimal transactionQty,

        String note
) {
}
