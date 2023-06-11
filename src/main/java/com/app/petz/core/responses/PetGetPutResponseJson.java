package com.app.petz.core.responses;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PetGetPutResponseJson(
    UUID id,
    String name,
    Integer age,
    LocalDate birthday,
    Double weight,
    String color,
    String mainImageUrl
) {
}

