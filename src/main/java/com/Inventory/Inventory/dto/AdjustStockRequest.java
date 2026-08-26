package com.Inventory.Inventory.dto;



import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AdjustStockRequest(

        @NotNull
        Long productId,

        @NotNull
        Long locationId,

        @NotNull
        @PositiveOrZero
        BigDecimal adjustedQty,

        @NotBlank
        String baseUnitName,

        String transactionUnitName,

        @NotNull
        @Positive
        BigDecimal transactionQty,

        @NotBlank
        String adjustmentMethod,

        @NotNull
        Integer adjustmentReason,

        String note
) {
}
