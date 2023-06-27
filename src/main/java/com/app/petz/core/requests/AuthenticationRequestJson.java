package com.app.petz.core.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthenticationRequestJson(
        @NotNull String email,
        @NotNull String password
) {
}
