package com.Inventory.Inventory.service;



import com.Inventory.Inventory.dto.AdjustStockRequest;
import com.Inventory.Inventory.dto.CreateStockRequest;
import com.Inventory.Inventory.dto.StockResponse;
import com.Inventory.Inventory.entity.Product;
import com.Inventory.Inventory.entity.Stock;
import com.Inventory.Inventory.exception.ProductNotFoundException;
import com.Inventory.Inventory.exception.StockNotFoundException;
import com.Inventory.Inventory.mapper.StockMapper;
import com.Inventory.Inventory.repository.ProductRepository;
import com.Inventory.Inventory.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    private final ProductRepository productRepository;

    private final StockMapper stockMapper;

    public StockResponse createStock(
            CreateStockRequest request) {

        Product product =
                productRepository.findById(
                        request.productId()
                ).orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found: "
                                        + request.productId()
                        ));

        Stock stock =
                stockRepository
                        .findByProductProductIdAndLocationId(
                                request.productId(),
                                request.locationId()
                        )
                        .orElse(null);

        if (stock == null) {

            stock = Stock.builder()
                    .product(product)
                    .locationId(request.locationId())
                    .locationName(request.locationName())
                    .quantityOnHand(
                            request.adjustedQty())
                    .availableQuantity(
                            request.adjustedQty())
                    .quantityBooked(
                            BigDecimal.ZERO)
                    .quantityInPO(
                            BigDecimal.ZERO)
                    .quantityInSO(
                            BigDecimal.ZERO)
                    .backOrderQuantity(
                            BigDecimal.ZERO)
                    .unitName(
                            request.baseUnitName())
                    .lastAdjustmentNote(
                            request.note())
                    .build();

        } else {

            stock.setQuantityOnHand(
                    stock.getQuantityOnHand()
                            .add(request.adjustedQty())
            );

            stock.setAvailableQuantity(
                    stock.getAvailableQuantity()
                            .add(request.adjustedQty())
            );

            stock.setLastAdjustmentNote(
                    request.note()
            );
        }

        Stock savedStock =
                stockRepository.save(stock);

        return stockMapper.toResponse(savedStock);
    }

    public StockResponse adjustStock(
            AdjustStockRequest request) {

        Stock stock =
                stockRepository
                        .findByProductProductIdAndLocationId(
                                request.productId(),
                                request.locationId()
                        )
                        .orElseThrow(() ->
                                new StockNotFoundException(
                                        "Stock not found for Product ID "
                                                + request.productId()
                                                + " and Location ID "
                                                + request.locationId()
                                ));

        String method =
                request.adjustmentMethod()
                        .trim()
                        .toUpperCase();

        BigDecimal quantity =
                request.transactionQty();

        switch (method) {

            case "ADD" -> {

                stock.setQuantityOnHand(
                        stock.getQuantityOnHand()
                                .add(quantity)
                );

                stock.setAvailableQuantity(
                        stock.getAvailableQuantity()
                                .add(quantity)
                );
            }

            case "DEDUCT" -> {

                if (stock.getAvailableQuantity()
                        .compareTo(quantity) < 0) {

                    throw new IllegalArgumentException(
                            "Insufficient available stock. "
                                    + "Available: "
                                    + stock.getAvailableQuantity()
                                    + ", Requested: "
                                    + quantity
                    );
                }

                stock.setQuantityOnHand(
                        stock.getQuantityOnHand()
                                .subtract(quantity)
                );

                stock.setAvailableQuantity(
                        stock.getAvailableQuantity()
                                .subtract(quantity)
                );
            }

            default -> throw new IllegalArgumentException(
                    "Invalid adjustment method. "
                            + "Allowed values: ADD or DEDUCT"
            );
        }

        stock.setLastAdjustmentNote(
                request.note()
        );

        Stock updatedStock =
                stockRepository.save(stock);

        return stockMapper.toResponse(updatedStock);
    }

    @Transactional(readOnly = true)
    public List<StockResponse> getStockByProduct(
            Long productId) {

        if (!productRepository.existsById(productId)) {

            throw new ProductNotFoundException(
                    "Product not found: "
                            + productId
            );
        }

        return stockRepository
                .findByProductProductId(productId)
                .stream()
                .map(stockMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StockResponse getStock(
            Long productId,
            Long locationId) {

        Stock stock =
                stockRepository
                        .findByProductProductIdAndLocationId(
                                productId,
                                locationId
                        )
                        .orElseThrow(() ->
                                new StockNotFoundException(
                                        "Stock not found"
                                ));

        return stockMapper.toResponse(stock);
    }
}
