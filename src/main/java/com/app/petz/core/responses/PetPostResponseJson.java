package com.app.petz.core.responses;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PetPostResponseJson(
    LocalDateTime timeStamp,
    String message,
    UUID petUuid
) {
}
