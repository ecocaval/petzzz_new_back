package com.app.petz.core.responses;

import com.app.petz.enums.ProductSizes;
import com.app.petz.model.Product;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductSizesGetResponseJson(
    List<ProductSizes> productSizes
){
}
