package com.app.petz.mapper;

import com.app.petz.core.responses.ProductSizesGetResponseJson;
import com.app.petz.enums.ProductSizes;
import com.app.petz.model.ProductSize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSizeMapper {

    public ProductSizes mapStringToProductSize(String size) {
        try {
            return ProductSizes.valueOf(size);
        } catch (IllegalArgumentException e) {
            return ProductSizes.M; // Default size is M
        }
    }

    public ProductSizesGetResponseJson productModelToGetResponseJson(List<ProductSize> productSizes) {
        List<ProductSizes> productGetResponseJson = new ArrayList<>();
        for (ProductSize productSize : productSizes) {
            productGetResponseJson.add(productSize.getSize());
        }
        return ProductSizesGetResponseJson.builder().productSizes(productGetResponseJson).build();
    }
}