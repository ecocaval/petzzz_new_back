package com.app.petz.service;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.exception.PetNotFoundException;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.repository.PetRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class PetService {

    private final PetMapper petMapper;

    private final PetRepository petRepository;

    public PetService(PetMapper petMapper, PetRepository petRepository) {
        this.petMapper = petMapper;
        this.petRepository = petRepository;
    }

    public Pet createPet(PetPostRequestJson petPostRequestJson) {
        Pet pet = petMapper.createRequestToPet(petPostRequestJson);
        return petRepository.save(pet);
    }

    public List<Pet> listAll() {
        List<Pet> pets = petRepository.findAll();
        return pets;
    }

    public Pet checkPetExistence(UUID id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found!"));
        if(pet.getRemoved()) throw new PetNotFoundException("Pet deleted!");

        return pet;
    }
    public PetGetResponseJson findById(UUID id) {
        Pet pet = checkPetExistence(id);

        return petMapper.petToGetResponseJson(pet);
    }


    public Pet replacePet(UUID id, PetPutRequestJson petPutRequestJson) {
        Pet petFinded = checkPetExistence(id);

        Pet petUpdated = petMapper.petPutRequestToPet(petFinded, petPutRequestJson);

        petRepository.save(petUpdated);

        return checkPetExistence(id);
    }

    public String deletePet(UUID id) {
        Pet petFinded = checkPetExistence(id);

        Pet petRemoved = Pet.builder()
                .id(id)
                .name(petFinded.getName())
                .creationDate(petFinded.getCreationDate())
                .removed(true)
                .age(petFinded.getAge())
                .birthday(petFinded.getBirthday())
                .weight(petFinded.getWeight())
                .color(petFinded.getColor())
                .build();

        petRepository.save(petDeleted);

        return petFinded.getName();
    }
}
