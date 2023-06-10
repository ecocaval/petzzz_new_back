package com.app.petz.core.requests;

import lombok.Builder;

@Builder
public record ProductPostRequestJson(
    String name,
    String description,
    Double weight,
    String sku,
    String mainImageUrl,
    Boolean hasLocalAcquire
) {
}
