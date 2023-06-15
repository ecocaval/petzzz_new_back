package com.app.petz.model.constraints;

import com.app.petz.enums.ProductSizes;
import com.app.petz.model.Product;

import java.io.Serializable;
import java.util.UUID;

public class ProductSizeId implements Serializable {
    private UUID id;
    private Product product;
    private ProductSizes size;
}

