package com.Inventory.Inventory.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateProductRequest(

        @NotBlank(message = "Product name is required")
        @Size(max = 200)
        String name,

        @Size(max = 2000)
        String description,

        @Size(max = 2000)
        String purchaseDesc,

        @Size(max = 2000)
        String note,

        @NotBlank(message = "Product number is required")
        @Size(max = 100)
        String productNumber,

        Boolean taxable,

        String unitName,

        String minQtyForLowStockUnit,

        String maxQtyForReorderUnit,

        @PositiveOrZero
        BigDecimal minQtyForLowStock,

        @PositiveOrZero
        BigDecimal maxQtyForReorder,

        Boolean freeProduct,

        @NotNull(message = "Purchase price is required")
        @PositiveOrZero(message = "Purchase price cannot be negative")
        BigDecimal purchasePrice,

        @NotNull(message = "Sale price is required")
        @PositiveOrZero(message = "Sale price cannot be negative")
        BigDecimal salePrice,

        Boolean published,

        LocalDateTime publishedDate,

        String defaultCategoryName,

        String subCategoryName,

        Boolean canSale,

        Boolean uniqueProduct,

        Boolean disabled,

        String disabledReason,

        String productType,

        String itemTrackingType,

        Long parentItemId,

        Boolean lotNumberEditable,

        Boolean lotNumberUnique,

        Boolean serialEditable,

        @PositiveOrZero
        BigDecimal lastDirectCost,

        @PositiveOrZero
        BigDecimal markupPercentage,

        Long organizationId
) {
}