package com.app.petz.core.responses;

import com.app.petz.core.dto.ProductMainInfoDto;

import java.time.LocalDateTime;

public record ProductDeleteResponseJson(String message, LocalDateTime stamp) {
    public ProductDeleteResponseJson(ProductMainInfoDto productMainInfoDto) {
        this("O produto '" + productMainInfoDto.name() +
                     "' com sku '" + productMainInfoDto.sku() +
                     "' foi excluido com sucesso!", LocalDateTime.now()
        );
    }
}
