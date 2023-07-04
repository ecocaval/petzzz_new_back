package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetDeleteResponseJson;
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

        BDDMockito.when(petServiceMock.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(PetCreator.createPetGetResponseJson());

        BDDMockito.when(petServiceMock.replace(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(PetPutRequestJson.class)))
                .thenReturn(PetCreator.createValidUpdatedPet());

        BDDMockito.when(petServiceMock.delete(ArgumentMatchers.any(UUID.class)))
                .thenReturn(PetCreator.createPetDeleteResponseJson());
    }

    @Test
    @DisplayName("create return PetPostResponseJson and status CREATED when successful")
    void create_ReturnsPetToPetPostResponseJson_WhenSuccessful(){
        PetPostRequestJson petPostRequestJson = PetCreator.createPetPostRequestJson();

        ResponseEntity<PetPostResponseJson> petCreated = this.petController.create(petPostRequestJson);

        Assertions.assertThat(petCreated.getBody())
                .isNotNull();
        Assertions.assertThat(petCreated.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("findById return PetGetResponseJson when successul ")
    void findById_ReturnsPetGetResponseJson_WhenSuccessul(){
        PetPostRequestJson petPostRequestJson = PetCreator.createPetPostRequestJson();

        ResponseEntity<PetPostResponseJson> petSaved = this.petController.create(petPostRequestJson);

        ResponseEntity<PetGetResponseJson> petFinded = this.petController.findById(Objects.requireNonNull(petSaved.getBody()).pet().getId());

        Assertions.assertThat(petFinded)
                .isNotNull();


    }

    @Test
    @DisplayName("replace update pet when successul")
    void replace_UpdatesPet_WhenSuccessul(){
        Pet petToBeUpdated = PetCreator.createValidPet();

        PetPutRequestJson petPutRequestJson = PetCreator.createPetPutRequestJson();

        Assertions.assertThatCode(() -> petController.replace(petToBeUpdated.getId(), petPutRequestJson))
                .doesNotThrowAnyException();


        ResponseEntity<PetGetResponseJson> entity = petController.replace(petToBeUpdated.getId(), petPutRequestJson);

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete update pet removed atribute when successul")
    void delete_UpdatesPetRemovedAtt_WhenSuccessul(){
        Pet petToBeDeleted = PetCreator.createValidPet();

        Assertions.assertThatCode(() -> petController.delete(petToBeDeleted.getId()))
                .doesNotThrowAnyException();


        ResponseEntity<PetDeleteResponseJson> entity = petController.delete(petToBeDeleted.getId());

        Assertions.assertThat(entity)
                .isNotNull();

        Assertions.assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

    }

}