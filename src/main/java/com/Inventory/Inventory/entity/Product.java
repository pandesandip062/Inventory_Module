package com.Inventory.Inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_number",
                        columnNames = "product_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(name = "purchase_desc", length = 2000)
    private String purchaseDesc;

    @Column(length = 2000)
    private String note;

    @Column(
            name = "product_number",
            nullable = false,
            length = 100
    )
    private String productNumber;

    @Column(name = "is_taxable")
    private Boolean taxable;

    @Column(name = "unit_name", length = 50)
    private String unitName;

    @Column(name = "min_qty_low_stock_unit", length = 50)
    private String minQtyForLowStockUnit;

    @Column(name = "max_qty_reorder_unit", length = 50)
    private String maxQtyForReorderUnit;

    @Column(
            name = "min_qty_low_stock",
            precision = 19,
            scale = 4
    )
    private BigDecimal minQtyForLowStock;

    @Column(
            name = "max_qty_reorder",
            precision = 19,
            scale = 4
    )
    private BigDecimal maxQtyForReorder;

    @Column(name = "is_free_product")
    private Boolean freeProduct;

    @Column(
            name = "purchase_price",
            precision = 19,
            scale = 4
    )
    private BigDecimal purchasePrice;

    @Column(
            name = "sale_price",
            precision = 19,
            scale = 4
    )
    private BigDecimal salePrice;

    @Column(name = "is_published")
    private Boolean published;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "default_category_name", length = 200)
    private String defaultCategoryName;

    @Column(name = "sub_category_name", length = 200)
    private String subCategoryName;

    @Column(name = "can_sale")
    private Boolean canSale;

    @Column(name = "is_unique")
    private Boolean uniqueProduct;

    @Column(name = "is_disabled")
    private Boolean disabled;

    @Column(name = "disabled_reason", length = 500)
    private String disabledReason;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "item_tracking_type", length = 50)
    private String itemTrackingType;

    @Column(name = "parent_item_id")
    private Long parentItemId;

    @Column(name = "lot_number_editable")
    private Boolean lotNumberEditable;

    @Column(name = "lot_number_unique")
    private Boolean lotNumberUnique;

    @Column(name = "serial_editable")
    private Boolean serialEditable;

    @Column(
            name = "last_direct_cost",
            precision = 19,
            scale = 4
    )
    private BigDecimal lastDirectCost;

    @Column(
            name = "markup_percentage",
            precision = 19,
            scale = 4
    )
    private BigDecimal markupPercentage;

    @Column(
            name = "margin_percentage",
            precision = 19,
            scale = 4
    )
    private BigDecimal marginPercentage;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (taxable == null) taxable = false;
        if (freeProduct == null) freeProduct = false;
        if (published == null) published = false;
        if (canSale == null) canSale = true;
        if (uniqueProduct == null) uniqueProduct = false;
        if (disabled == null) disabled = false;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}