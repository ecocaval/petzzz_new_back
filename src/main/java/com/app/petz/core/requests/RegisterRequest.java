package com.app.petz.core.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotNull String name,
        @NotNull String email,
        @NotNull String password,
        @NotNull String birthday,
        @NotNull String cpf,
        String mainImageUrl
) {
}


