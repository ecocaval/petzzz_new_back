package com.app.petz.core.requests;

import lombok.Builder;

@Builder
public record PetPutRequestJson(
    String name,
    Integer age,
    String birthday,
    Double weight,
    String color,
    String mainImageUrl
) {
}
