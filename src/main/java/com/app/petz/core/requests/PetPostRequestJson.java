package com.app.petz.core.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PetPostRequestJson(
    @NotNull String name,
    @NotNull Integer age,
    @NotNull String birthday,
    Double weight,
    String color,
    String mainImageUrl
) {
}

