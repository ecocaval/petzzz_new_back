package com.app.petz.core.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPostRequestJson {
    private String name;
    private String description;
    private String sku;
    private String mainImageUrl;
    private Boolean hasLocalAcquire;
}
