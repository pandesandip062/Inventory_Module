package com.Inventory.Inventory.controller;


import com.Inventory.Inventory.dto.AdjustStockRequest;
import com.Inventory.Inventory.dto.CreateStockRequest;
import com.Inventory.Inventory.dto.StockResponse;
import com.Inventory.Inventory.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponse> createStock(
            @Valid
            @RequestBody
            CreateStockRequest request) {

        StockResponse response =
                stockService.createStock(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/adjust")
    public ResponseEntity<StockResponse> adjustStock(
            @Valid
            @RequestBody
            AdjustStockRequest request) {

        StockResponse response =
                stockService.adjustStock(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockResponse>>
    getStockByProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                stockService.getStockByProduct(
                        productId
                )
        );
    }

    @GetMapping(
            "/product/{productId}/location/{locationId}"
    )
    public ResponseEntity<StockResponse> getStock(
            @PathVariable Long productId,

            @PathVariable Long locationId) {

        return ResponseEntity.ok(
                stockService.getStock(
                        productId,
                        locationId
                )
        );
    }
}
