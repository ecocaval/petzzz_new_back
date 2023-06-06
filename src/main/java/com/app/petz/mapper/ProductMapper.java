package com.app.petz.mapper;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductMapper {

    public Product productPostRequestJsonToProduct(ProductPostRequestJson productPostRequestJson) {
        return Product.builder()
                .name(productPostRequestJson.getName())
                .removed(false)
                .creationDate(LocalDateTime.now())
                .description(productPostRequestJson.getDescription())
                .sku(productPostRequestJson.getSku())
                .mainImageUrl(productPostRequestJson.getMainImageUrl())
                .hasLocalAcquire(productPostRequestJson.getHasLocalAcquire())
                .build();
    }
    public ProductPostResponseJson productToProductPostResponseJson(Product product) {
        return ProductPostResponseJson.builder()
                .timestamp(LocalDateTime.now())
                .message("O produto '" + product.getName() +
                        "' foi criado com sucesso!")
                .id(product.getId())
                .build();
    }

    public Product productPutRequestToProduct(Product product, ProductPutRequestJson productPutRequestJson) {
        return Product.builder()
                .id(product.getId())
                .name(productPutRequestJson.getName() != null ? productPutRequestJson.getName() : product.getName())
                .removed(product.getRemoved())
                .creationDate(product.getCreationDate())
                .description(productPutRequestJson.getDescription() != null ? productPutRequestJson.getDescription() : product.getDescription())
                .sku(productPutRequestJson.getSku() != null ? productPutRequestJson.getSku() : product.getSku())
                .mainImageUrl(productPutRequestJson.getMainImageUrl() != null ? productPutRequestJson.getMainImageUrl() : product.getMainImageUrl())
                .hasLocalAcquire(productPutRequestJson.getHasLocalAcquire() != null ? productPutRequestJson.getHasLocalAcquire() : product.getHasLocalAcquire())
                .build();
    }
}
