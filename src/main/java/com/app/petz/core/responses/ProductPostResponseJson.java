package com.app.petz.core.responses;

import com.app.petz.model.Product;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductPostResponseJson (
    LocalDateTime timestamp,
    String message,
    Product product
){
}
