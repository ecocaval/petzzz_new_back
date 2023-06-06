package com.app.petz.core.responses;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PetDeleteResponseJson {
    private final String message;
    private final LocalDateTime stamp;

    public PetDeleteResponseJson(String petName) {
        this.message = "O pet " + petName + " foi excluido com sucesso!";
        this.stamp = LocalDateTime.now();
    }
}
