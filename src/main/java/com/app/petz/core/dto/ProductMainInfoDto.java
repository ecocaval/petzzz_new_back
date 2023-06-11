package com.app.petz.core.dto;

import lombok.Builder;

@Builder
public record ProductMainInfoDto(String name, String sku) {
}
