package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.factory.PetCreator;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.service.PetService;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
class PetControllerTest {
    @InjectMocks
    private PetController petController;
    @Mock
    private PetService petServiceMock;
    @Mock
    private PetMapper petMapperMock;

    @BeforeEach
    void setup(){
        BDDMockito.when(petServiceMock.createPet(ArgumentMatchers.any(PetPostRequestJson.class)))
                .thenReturn(PetCreator.createValidPet());
        BDDMockito.when(petMapperMock.createRequestToPet(ArgumentMatchers.any(PetPostRequestJson.class)))
                .thenReturn(PetCreator.createValidPet());
        BDDMockito.when(petMapperMock.petToResponseJson(ArgumentMatchers.any(Pet.class)))
                .thenReturn(PetCreator.createPetPostResponseJson());


    }

    @Test
    @DisplayName("createPet return PetPostResponseJson and status CREATED when successful")
    void createPet_ReturnsPetToPetPostResponseJson_WhenSuccessful(){
        PetPostRequestJson petPostRequestJson = PetCreator.createPetPostRequestJson();

        ResponseEntity<PetPostResponseJson> petCreated = this.petController.createPet(petPostRequestJson);

        Assertions.assertThat(petCreated.getBody())
                .isNotNull();
        Assertions.assertThat(petCreated.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("findById return PetPostResponseJson when successul ")
    void findById_ReturnsPetGetResponseJson_WhenSuccessul(){
//        this.petController.findById()
    }

}