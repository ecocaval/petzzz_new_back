package com.app.petz.core.responses;

import java.time.LocalDateTime;


public record PetDeleteResponseJson(String message, LocalDateTime stamp) {

    public PetDeleteResponseJson(String petName) {
        this("O pet '" + petName + "' foi excluido com sucesso!", LocalDateTime.now());
    }
}
