package com.app.petz.core.responses;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PetGetPutResponseJson(
    UUID id,
    LocalDateTime creationDate,
    String name,
    Integer age,
    LocalDate birthday,
    Double weight,
    String color,
    String mainImageUrl
) {
}

