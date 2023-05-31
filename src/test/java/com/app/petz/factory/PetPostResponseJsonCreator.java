package com.app.petz.factory;

import com.app.petz.core.responses.PetPostResponseJson;

import java.time.LocalDateTime;

public class PetPostResponseJsonCreator {
    public static PetPostResponseJson createPetPostResponseJson(){
        return PetPostResponseJson.builder()
                .petUuid(PetCreator.createPetToBeSaved().getId())
                .timeStamp(LocalDateTime.now())
                .message("O pet " + PetCreator.createPetToBeSaved().getName() + " foi criado com sucesso.")
                .build();
    }
}
