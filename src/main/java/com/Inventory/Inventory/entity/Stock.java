package com.Inventory.Inventory.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_product_location",
                        columnNames = {
                                "product_id",
                                "location_id"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_stock_product"
            )
    )
    private Product product;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "location_name", length = 200)
    private String locationName;

    @Column(
            name = "quantity_on_hand",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal quantityOnHand;

    @Column(
            name = "available_quantity",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal availableQuantity;

    @Column(
            name = "quantity_booked",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal quantityBooked;

    @Column(
            name = "quantity_in_po",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal quantityInPO;

    @Column(
            name = "quantity_in_so",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal quantityInSO;

    @Column(
            name = "back_order_quantity",
            precision = 19,
            scale = 4,
            nullable = false
    )
    private BigDecimal backOrderQuantity;

    @Column(name = "unit_name", length = 50)
    private String unitName;

    @Column(name = "last_adjustment_note", length = 1000)
    private String lastAdjustmentNote;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (quantityOnHand == null)
            quantityOnHand = BigDecimal.ZERO;

        if (availableQuantity == null)
            availableQuantity = BigDecimal.ZERO;

        if (quantityBooked == null)
            quantityBooked = BigDecimal.ZERO;

        if (quantityInPO == null)
            quantityInPO = BigDecimal.ZERO;

        if (quantityInSO == null)
            quantityInSO = BigDecimal.ZERO;

        if (backOrderQuantity == null)
            backOrderQuantity = BigDecimal.ZERO;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
