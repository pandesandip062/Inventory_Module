package com.Inventory.Inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(

        Long productId,
        String name,
        String description,
        String purchaseDesc,
        String note,
        String productNumber,
        Boolean taxable,
        String unitName,
        String minQtyForLowStockUnit,
        String maxQtyForReorderUnit,
        BigDecimal minQtyForLowStock,
        BigDecimal maxQtyForReorder,
        Boolean freeProduct,
        BigDecimal purchasePrice,
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
        BigDecimal lastDirectCost,
        BigDecimal markupPercentage,
        BigDecimal marginPercentage,
        Long organizationId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}