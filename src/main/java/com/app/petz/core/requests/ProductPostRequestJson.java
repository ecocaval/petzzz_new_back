package com.app.petz.core.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductPostRequestJson(
    @NotNull String name,
    String description,
    @NotNull Double weight,
    String sku,
    String sizes, // example: "P,G,XG"
    String mainImageUrl,
    Boolean hasLocalAcquire
) {
}
