package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
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

import java.util.Objects;
import java.util.UUID;

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
        BDDMockito.when(petServiceMock.create(ArgumentMatchers.any(PetPostRequestJson.class)))
                .thenReturn(PetCreator.createPetPostResponseJson());

        BDDMockito.when(petMapperMock.createRequestToPet(ArgumentMatchers.any(PetPostRequestJson.class)))
                .thenReturn(PetCreator.createValidPet());

        BDDMockito.when(petMapperMock.petToPostResponseJson(ArgumentMatchers.any(Pet.class)))
                .thenReturn(PetCreator.createPetPostResponseJson());

        BDDMockito.when(petServiceMock.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(PetCreator.createPetGetResponseJson());

        BDDMockito.doNothing().when(petServiceMock).replace(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(PetPutRequestJson.class));
    }

    @Test
    @DisplayName("createPet return PetPostResponseJson and status CREATED when successful")
    void createPet_ReturnsPetToPetPostResponseJson_WhenSuccessful(){
        PetPostRequestJson petPostRequestJson = PetCreator.createPetPostRequestJson();

        ResponseEntity<PetPostResponseJson> petCreated = this.petController.create(petPostRequestJson);

        Assertions.assertThat(petCreated.getBody())
                .isNotNull();
        Assertions.assertThat(petCreated.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("findById return PetPostResponseJson when successul ")
    void findById_ReturnsPetGetResponseJson_WhenSuccessul(){
        PetPostRequestJson petPostRequestJson = PetCreator.createPetPostRequestJson();

        ResponseEntity<PetPostResponseJson> petSaved = this.petController.create(petPostRequestJson);

        ResponseEntity<PetGetResponseJson> petFinded = this.petController.findById(Objects.requireNonNull(petSaved.getBody()).pet().getId());

        Assertions.assertThat(petFinded)
                .isNotNull();


    }

    @Test
    @DisplayName("replacePet update pet when successul")
    void replacePet_UpdatesPet_WhenSuccessul(){
        Pet petToBeUpdated = PetCreator.createValidPet();

        PetPutRequestJson petPutRequestJson = PetCreator.createPetPutRequestJson();

        Assertions.assertThatCode(() -> petController.replace(petToBeUpdated.getId(), petPutRequestJson))
                .doesNotThrowAnyException();

        var entity = petController.replace(petToBeUpdated.getId(), petPutRequestJson);

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("deletePet update pet removed atribute when successul")
    void deletePet_UpdatesPetRemovedAtt_WhenSuccessul(){
        Pet petToBeDeleted = PetCreator.createValidPet();

        Assertions.assertThatCode(() -> petController.delete(petToBeDeleted.getId()))
                .doesNotThrowAnyException();

        var entity = petController.delete(petToBeDeleted.getId());

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);

    }

}