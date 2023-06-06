package com.app.petz.core.requests;

import lombok.Builder;

@Builder
public record ProductPutRequestJson(
    String name,
    String description,
    String sku,
    String mainImageUrl,
    Boolean hasLocalAcquire
) {
}
