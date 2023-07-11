package com.app.petz.model;

import com.app.petz.core.dto.ProductMainInfoDto;
import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductGetPutResponseJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean removed = false;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false)
    private String name;

    @Column()
    private Double weight;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column()
    private String mainImageUrl;

    @Column(nullable = false)
    private Boolean hasLocalAcquire;

    public static Product fromPostRequestJson(ProductPostRequestJson productPostRequestJson) {
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

    public static Product fromProductPutRequest(Product product, ProductPutRequestJson productPutRequestJson) {
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

    public static ProductPostResponseJson toPostResponseJson(Product product) {
        return ProductPostResponseJson.builder()
                .timestamp(LocalDateTime.now())
                .message("O produto '" + product.getName() + "' foi criado com sucesso!")
                .product(product)
                .build();
    }

    public static ProductMainInfoDto toMainInfoDto(Product product) {
        return ProductMainInfoDto.builder()
                .name(product.getName())
                .sku(product.getSku())
                .build();
    }

    public static ProductGetPutResponseJson toGetPutResponseJson(Product product) {
        return ProductGetPutResponseJson.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .weight(product.getWeight())
                .sku(product.getSku())
                .mainImageUrl(product.getMainImageUrl())
                .hasLocalAcquire(product.getHasLocalAcquire())
                .build();
    }
}
