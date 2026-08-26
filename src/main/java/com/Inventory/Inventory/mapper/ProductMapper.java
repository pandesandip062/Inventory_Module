package com.Inventory.Inventory.mapper;




import com.Inventory.Inventory.dto.CreateProductRequest;
import com.Inventory.Inventory.dto.ProductResponse;
import com.Inventory.Inventory.dto.UpdateProductRequest;
import com.Inventory.Inventory.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {

        return Product.builder()
                .name(request.name())
                .description(request.description())
                .purchaseDesc(request.purchaseDesc())
                .note(request.note())
                .productNumber(request.productNumber())
                .taxable(request.taxable())
                .unitName(request.unitName())
                .minQtyForLowStockUnit(
                        request.minQtyForLowStockUnit())
                .maxQtyForReorderUnit(
                        request.maxQtyForReorderUnit())
                .minQtyForLowStock(
                        request.minQtyForLowStock())
                .maxQtyForReorder(
                        request.maxQtyForReorder())
                .freeProduct(request.freeProduct())
                .purchasePrice(request.purchasePrice())
                .salePrice(request.salePrice())
                .published(request.published())
                .publishedDate(request.publishedDate())
                .defaultCategoryName(
                        request.defaultCategoryName())
                .subCategoryName(
                        request.subCategoryName())
                .canSale(request.canSale())
                .uniqueProduct(request.uniqueProduct())
                .disabled(request.disabled())
                .disabledReason(request.disabledReason())
                .productType(request.productType())
                .itemTrackingType(
                        request.itemTrackingType())
                .parentItemId(request.parentItemId())
                .lotNumberEditable(
                        request.lotNumberEditable())
                .lotNumberUnique(
                        request.lotNumberUnique())
                .serialEditable(
                        request.serialEditable())
                .lastDirectCost(
                        request.lastDirectCost())
                .markupPercentage(
                        request.markupPercentage())
                .organizationId(
                        request.organizationId())
                .build();
    }

    public void updateEntity(
            Product product,
            UpdateProductRequest request) {

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPurchaseDesc(request.purchaseDesc());
        product.setNote(request.note());
        product.setProductNumber(request.productNumber());
        product.setTaxable(request.taxable());
        product.setUnitName(request.unitName());

        product.setMinQtyForLowStockUnit(
                request.minQtyForLowStockUnit());

        product.setMaxQtyForReorderUnit(
                request.maxQtyForReorderUnit());

        product.setMinQtyForLowStock(
                request.minQtyForLowStock());

        product.setMaxQtyForReorder(
                request.maxQtyForReorder());

        product.setFreeProduct(
                request.freeProduct());

        product.setPurchasePrice(
                request.purchasePrice());

        product.setSalePrice(
                request.salePrice());

        product.setPublished(
                request.published());

        product.setPublishedDate(
                request.publishedDate());

        product.setDefaultCategoryName(
                request.defaultCategoryName());

        product.setSubCategoryName(
                request.subCategoryName());

        product.setCanSale(
                request.canSale());

        product.setUniqueProduct(
                request.uniqueProduct());

        product.setDisabled(
                request.disabled());

        product.setDisabledReason(
                request.disabledReason());

        product.setProductType(
                request.productType());

        product.setItemTrackingType(
                request.itemTrackingType());

        product.setParentItemId(
                request.parentItemId());

        product.setLotNumberEditable(
                request.lotNumberEditable());

        product.setLotNumberUnique(
                request.lotNumberUnique());

        product.setSerialEditable(
                request.serialEditable());

        product.setLastDirectCost(
                request.lastDirectCost());

        product.setMarkupPercentage(
                request.markupPercentage());

        product.setOrganizationId(
                request.organizationId());
    }

    public ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPurchaseDesc(),
                product.getNote(),
                product.getProductNumber(),
                product.getTaxable(),
                product.getUnitName(),
                product.getMinQtyForLowStockUnit(),
                product.getMaxQtyForReorderUnit(),
                product.getMinQtyForLowStock(),
                product.getMaxQtyForReorder(),
                product.getFreeProduct(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getPublished(),
                product.getPublishedDate(),
                product.getDefaultCategoryName(),
                product.getSubCategoryName(),
                product.getCanSale(),
                product.getUniqueProduct(),
                product.getDisabled(),
                product.getDisabledReason(),
                product.getProductType(),
                product.getItemTrackingType(),
                product.getParentItemId(),
                product.getLotNumberEditable(),
                product.getLotNumberUnique(),
                product.getSerialEditable(),
                product.getLastDirectCost(),
                product.getMarkupPercentage(),
                product.getMarginPercentage(),
                product.getOrganizationId(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}