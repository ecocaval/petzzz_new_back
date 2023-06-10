package com.app.petz.core.responses;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ProductGetResponseJson(
    UUID id,
    String name,
    String description,
    Double weight,
    String sku,
    String mainImageUrl,
    Boolean hasLocalAcquire
) {
}
