package com.app.petz.controller;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.factory.PetCoreDtoCreator;
import com.app.petz.factory.PetCreator;
import com.app.petz.factory.PetPostResponseJsonCreator;
import com.app.petz.model.Pet;
import com.app.petz.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

class PetControllerTest {
    @InjectMocks
    private PetController petController;
    @Mock
    private PetService petServiceMock;

    void setup(){
        BDDMockito.when(petServiceMock.createPet(ArgumentMatchers.any(PetPostRequestJson.class)))
                .thenReturn(PetCreator.createPetToBeSaved());
    }

    @Test
    @DisplayName("createPet return petToResponseJson and status CREATED when successful")
    void createPet_ReturnsPetToResponseJson_WhenSuccessful(){
        UUID expectedId = PetCreator.createPetToBeSaved().getId();
    }

}