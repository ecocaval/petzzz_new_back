package com.app.petz.repository;

import com.app.petz.factory.PetCreator;
import com.app.petz.model.Pet;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@DataJpaTest
@DisplayName("Testes para PetRepository")
class PetRepositoryTest {
    @Autowired
    private PetRepository petRepository;


    @Test
    @DisplayName("save cria um Pet em caso de Sucesso")
    void save_PersitsPet_WhenSuccessul() {
        Pet petToBeSaved = PetCreator.createPetToBeSaved();

        Pet savedPet = this.petRepository.save(petToBeSaved);

        Assertions.assertThat(savedPet)
                .isNotNull();

        Assertions.assertThat(savedPet.getId())
                .isNotNull();

        Assertions.assertThat(savedPet.getName())
                .isEqualTo(petToBeSaved.getName());
    }

    @Test
    @DisplayName("save atualiza um Pet em caso de Sucesso")
    void save_UpdatesPet_WhenSuccessul() {
        Pet petToBeSaved = PetCreator.createPetToBeSaved();

        Pet savedPet = this.petRepository.save(petToBeSaved);

        savedPet.setName("Doguinho Atualizado");

        Pet updatedPet = this.petRepository.save(savedPet);

        Assertions.assertThat(savedPet)
                .isNotNull();

        Assertions.assertThat(savedPet.getId())
                .isNotNull()
                .isEqualTo(updatedPet.getId());

        Assertions.assertThat(savedPet.getName())
                .isEqualTo(updatedPet.getName());
    }

    @Test
    @DisplayName("save atualiza removed de um Pet para true em caso de Sucesso")
    void save_RemovesPet_WhenSuccessul() {
        Pet petToBeDeleted = PetCreator.createPetToBeSaved();

        Pet deletedPet = this.petRepository.save(petToBeDeleted);

        deletedPet.setRemoved(true);

        Pet updatedPet = this.petRepository.save(deletedPet);

        Assertions.assertThat(deletedPet)
                .isNotNull();

        Assertions.assertThat(deletedPet.getId())
                .isNotNull()
                .isEqualTo(updatedPet.getId());

        Assertions.assertThat(deletedPet.getName())
                .isEqualTo(updatedPet.getName());

        Assertions.assertThat(deletedPet.getRemoved())
                .isTrue();
    }

//    @Test
//    @DisplayName("findByName retorna lista de Pets em caso de Sucesso")
//    void findByName_ReturnsListOfPet_WhenSuccessul(){
//        Pet petToBeSaved = PetCreator.createPetToBeSaved();
//
//        Pet pet = this.petRepository.save(petToBeSaved);
//
//        String name = pet.getName();
//
//        List<Pet> pets = petRepository.findByName(name);
//
//        Assertions.assertThat(pets)
//                .isNotEmpty()
//                .contains(pet);
//    }
//    @Test
//    @DisplayName("findByName retorna lista vazia no caso do Pet não existir")
//    void findByName_ReturnsEmptyList_WhenPetNotFound(){
//        List<Pet> pets = petRepository.findByName("Nome qualquer");
//
//        Assertions.assertThat(pets)
//                .isEmpty();
//    }

    @Test
    @DisplayName("findById retorna Pet em caso de Sucesso")
    void findById_ReturnsPet_WhenSuccessul() {
        Pet petToBeSaved = PetCreator.createPetToBeSaved();

        Pet petSaved = this.petRepository.save(petToBeSaved);

        UUID id = petSaved.getId();

        Optional<Pet> petFinded = petRepository.findById(id);

        Assertions.assertThat(petFinded)
                .isNotNull();

        Assertions.assertThat(petFinded.get().getId())
                .isEqualTo(id);

    }

}