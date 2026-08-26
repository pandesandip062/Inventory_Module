package com.Inventory.Inventory.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateProductRequest(

        @NotBlank(message = "Product name is required")
        String name,

        String description,

        String purchaseDesc,

        String note,

        @NotBlank(message = "Product number is required")
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

        @NotNull
        @PositiveOrZero
        BigDecimal purchasePrice,

        @NotNull
        @PositiveOrZero
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
