package com.app.petz.mapper;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    public ProductPostResponseJson productToProductResponseJson(Product product) {
        return ProductPostResponseJson.builder()
                .timestamp(LocalDateTime.now())
                .message("O produto '" + product.getName() +
                        "' foi criado com sucesso!")
                .id(product.getId())
                .build();
    }
}
