package com.Inventory.Inventory.service;


import com.Inventory.Inventory.dto.CreateProductRequest;
import com.Inventory.Inventory.dto.ProductResponse;
import com.Inventory.Inventory.dto.UpdateProductRequest;
import com.Inventory.Inventory.entity.Product;
import com.Inventory.Inventory.exception.ProductAlreadyExistsException;
import com.Inventory.Inventory.exception.ProductNotFoundException;
import com.Inventory.Inventory.mapper.ProductMapper;
import com.Inventory.Inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductResponse create(
            CreateProductRequest request) {

        if (productRepository.existsByProductNumber(
                request.productNumber())) {

            throw new ProductAlreadyExistsException(
                    "Product number already exists: "
                            + request.productNumber()
            );
        }

        validatePrices(
                request.purchasePrice(),
                request.salePrice()
        );

        Product product =
                productMapper.toEntity(request);

        calculateMargin(product);

        Product savedProduct =
                productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    public ProductResponse update(
            Long productId,
            UpdateProductRequest request) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found: "
                                                + productId
                                ));

        productRepository
                .findByProductNumber(
                        request.productNumber())
                .ifPresent(existing -> {

                    if (!existing.getProductId()
                            .equals(productId)) {

                        throw new ProductAlreadyExistsException(
                                "Product number already exists: "
                                        + request.productNumber()
                        );
                    }
                });

        validatePrices(
                request.purchasePrice(),
                request.salePrice()
        );

        productMapper.updateEntity(
                product,
                request
        );

        calculateMargin(product);

        Product updatedProduct =
                productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Long productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found: "
                                                + productId
                                ));

        return productMapper.toResponse(product);
    }

    public void delete(Long productId) {

        if (!productRepository.existsById(productId)) {

            throw new ProductNotFoundException(
                    "Product not found: "
                            + productId
            );
        }

        productRepository.deleteById(productId);
    }

    private void validatePrices(
            java.math.BigDecimal purchasePrice,
            java.math.BigDecimal salePrice) {

        if (salePrice.compareTo(purchasePrice) < 0) {

            throw new IllegalArgumentException(
                    "Sale price cannot be less than purchase price"
            );
        }
    }

    private void calculateMargin(Product product) {

        if (product.getSalePrice() == null ||
                product.getSalePrice()
                        .compareTo(java.math.BigDecimal.ZERO) == 0) {

            product.setMarginPercentage(
                    java.math.BigDecimal.ZERO
            );

            return;
        }

        if (product.getPurchasePrice() == null) {
            return;
        }

        java.math.BigDecimal margin =
                product.getSalePrice()
                        .subtract(product.getPurchasePrice())
                        .divide(
                                product.getSalePrice(),
                                4,
                                java.math.BigDecimal.ROUND_HALF_UP
                        )
                        .multiply(
                                java.math.BigDecimal.valueOf(100)
                        );

        product.setMarginPercentage(margin);
    }
}