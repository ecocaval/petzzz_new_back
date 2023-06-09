package com.app.petz.core.responses;

import com.app.petz.model.Pet;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PetPostResponseJson(
    LocalDateTime timeStamp,
    String message,
    Pet pet
) {
}
