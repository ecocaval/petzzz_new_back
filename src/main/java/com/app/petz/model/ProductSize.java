package com.app.petz.model;

import com.app.petz.core.responses.ProductSizesGetResponseJson;
import com.app.petz.enums.ProductSizes;
import com.app.petz.model.constraints.ProductSizeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ProductSize")
@IdClass(ProductSizeId.class)
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean removed = false;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductSizes size;

    public static ProductSizes mapStringToProductSize(String size) {
        try {
            return ProductSizes.valueOf(size);
        } catch (IllegalArgumentException e) {
            return ProductSizes.M; // Default size is M
        }
    }

    public static ProductSizesGetResponseJson toGetResponseJson(List<ProductSize> productSizes) {
        List<ProductSizes> productGetResponseJson = new ArrayList<>();
        for (ProductSize productSize : productSizes) {
            productGetResponseJson.add(productSize.getSize());
        }
        return ProductSizesGetResponseJson.builder().productSizes(productGetResponseJson).build();
    }

}