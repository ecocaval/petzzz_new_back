package com.app.petz.core.responses;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CustomerGetResponseJson(
    UUID id,
    String name,
    LocalDate birthday,
    Double weight,
    String cpf,
    String email,
    String mainImageUrl
) {
}

