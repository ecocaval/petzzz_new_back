package com.app.petz.core.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductGetPutResponseJson(
    UUID id,
    String name,
    String description,
    Double weight,
    String sku,
    String mainImageUrl,
    Boolean hasLocalAcquire
) {
}
