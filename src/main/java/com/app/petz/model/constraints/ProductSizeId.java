package com.app.petz.model.constraints;

import com.app.petz.enums.ProductSizes;

import java.io.Serializable;
import java.util.UUID;

public class ProductSizeId implements Serializable {
    private UUID id;
    private UUID productId;
    private ProductSizes size;
}

