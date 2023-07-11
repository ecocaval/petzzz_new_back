package com.app.petz.service;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.exception.PetNotFoundException;
import com.app.petz.factory.PetCreator;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.repository.PetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class PetServiceTest {
    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepositoryMock;
    @Mock
    private PetMapper petMapperMock;

    @BeforeEach
    void setup(){
        BDDMockito.when(petRepositoryMock.save(ArgumentMatchers.any(Pet.class)))
                .thenReturn(PetCreator.createValidPet());
        BDDMockito.when(petRepositoryMock.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(PetCreator.createValidPet()));

    }

    @Test
    @DisplayName("createPet return Pet when successful")
    void createPet_ReturnsPet_WhenSuccessful(){
        UUID expectedId = PetCreator.createValidPet().getId();

        PetPostResponseJson petCreated = petService.create(PetCreator.createPetPostRequestJson());

        Assertions.assertThat(petCreated)
                .isNotNull();

        Assertions.assertThat(petCreated.pet().getId())
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById return PetGetResponseJson when successul ")
    void findById_ReturnsPetGetResponseJson_WhenSuccessul(){
        UUID expectedId = PetCreator.createPetGetResponseJson().id();

        PetGetResponseJson petFinded = petService.findById(expectedId);


        Assertions.assertThat(petFinded)
                .isNotNull();

        Assertions.assertThat(petFinded.id())
                .isEqualTo(expectedId);
    }
    @Test
    @DisplayName("FindById throws PetNotFoundException when pet is not found")
    void findById_ThrowsPetNotFoundException_WhenPetNotFound(){
        BDDMockito.when(petRepositoryMock.findById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(PetNotFoundException.class)
                .isThrownBy(() -> petService.findById(UUID.randomUUID()));

    }

    @Test
    @DisplayName("replacePet update pet when successul")
    void replacePet_UpdatesPet_WhenSuccessul(){
        Assertions.assertThatCode(() -> petService.replace(PetCreator.createValidPet().getId(), PetCreator.createPetPutRequestJson()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("deletePet update pet removed atribute when successul")
    void deletePet_UpdatesPetRemovedAtt_WhenSuccessul(){
        Assertions.assertThatCode(() -> petService.delete(PetCreator.createValidPet().getId()))
                .doesNotThrowAnyException();


    }
}
