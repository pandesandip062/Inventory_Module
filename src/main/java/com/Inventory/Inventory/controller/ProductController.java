package com.Inventory.Inventory.controller;

import com.Inventory.Inventory.dto.CreateProductRequest;
import com.Inventory.Inventory.dto.ProductResponse;
import com.Inventory.Inventory.dto.UpdateProductRequest;
import com.Inventory.Inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid
            @RequestBody
            CreateProductRequest request) {

        ProductResponse response =
                productService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,

            @Valid
            @RequestBody
            UpdateProductRequest request) {

        ProductResponse response =
                productService.update(
                        productId,
                        request
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productService.getById(productId)
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {

        productService.delete(productId);

        return ResponseEntity.noContent().build();
    }
}