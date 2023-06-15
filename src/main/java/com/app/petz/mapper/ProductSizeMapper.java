package com.app.petz.mapper;

import com.app.petz.enums.ProductSizes;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeMapper {

    public ProductSizes mapStringToProductSize(String size) {
        try {
            return ProductSizes.valueOf(size);
        } catch (IllegalArgumentException e) {
            return ProductSizes.M; // Default size is M
        }
    }
}