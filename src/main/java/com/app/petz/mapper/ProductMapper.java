package com.app.petz.mapper;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductGetResponseJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    public Product productPostRequestJsonToProduct(ProductPostRequestJson productPostRequestJson) {
        return Product.builder()
                .name(productPostRequestJson.name())
                .removed(false)
                .creationDate(LocalDateTime.now())
                .description(productPostRequestJson.description())
                .weight(productPostRequestJson.weight())
                .sku(productPostRequestJson.sku())
                .mainImageUrl(productPostRequestJson.mainImageUrl())
                .hasLocalAcquire(productPostRequestJson.hasLocalAcquire())
                .build();
    }
    public ProductPostResponseJson productToProductPostResponseJson(Product product) {
        return ProductPostResponseJson.builder()
                .timestamp(LocalDateTime.now())
                .message("O produto '" + product.getName() + "' foi criado com sucesso!")
                .id(product.getId())
                .build();
    }

    public ProductGetResponseJson productToProductGetResponseJson(Product product) {
        return ProductGetResponseJson.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .weight(product.getWeight())
                .sku(product.getSku())
                .mainImageUrl(product.getMainImageUrl())
                .hasLocalAcquire(product.getHasLocalAcquire())
                .build();
    }

    public Product productPutRequestToProduct(Product product, ProductPutRequestJson productPutRequestJson) {
        return Product.builder()
                .id(product.getId())
                .name(productPutRequestJson.name() != null ? productPutRequestJson.name() : product.getName())
                .removed(product.getRemoved())
                .creationDate(product.getCreationDate())
                .description(productPutRequestJson.description() != null ? productPutRequestJson.description() : product.getDescription())
                .weight(productPutRequestJson.weight() != null ? productPutRequestJson.weight() : product.getWeight())
                .sku(productPutRequestJson.sku() != null ? productPutRequestJson.sku() : product.getSku())
                .mainImageUrl(productPutRequestJson.mainImageUrl() != null ? productPutRequestJson.mainImageUrl() : product.getMainImageUrl())
                .hasLocalAcquire(productPutRequestJson.hasLocalAcquire() != null ? productPutRequestJson.hasLocalAcquire() : product.getHasLocalAcquire())
                .build();
    }
}
