package com.app.petz.service;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetPutResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.exception.PetNotFoundException;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;

    public List<PetGetPutResponseJson> findAll() {
        return petRepository.findAllNotRemoved()
                .stream()
                .map(petMapper::petToGetPutResponseJson)
                .toList();
    }

    public PetGetPutResponseJson findById(UUID id) {
        Pet pet = checkPetExistence(id);

        return petMapper.petToGetPutResponseJson(pet);
    }

    public Pet checkPetExistence(UUID id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("O pet solicitado não foi encontrado!"));
        if(pet.getRemoved()) throw new PetNotFoundException("O pet solicitado foi deletado!");

        return pet;
    }

    public PetPostResponseJson create(PetPostRequestJson petPostRequestJson) {
        Pet pet = petMapper.createRequestToPet(petPostRequestJson);
        return petMapper.petToResponseJson(petRepository.save(pet));
    }

    public PetGetPutResponseJson replace(UUID id, PetPutRequestJson petPutRequestJson) {
        Pet pet = checkPetExistence(id);

        Pet petUpdated = petMapper.petPutRequestToPet(pet, petPutRequestJson);

        petRepository.save(petUpdated);

        return petMapper.petToGetPutResponseJson(checkPetExistence(id));
    }

    public String delete(UUID id) {
        Pet pet = checkPetExistence(id);

        Pet petRemoved = Pet.builder()
                .id(id)
                .name(pet.getName())
                .creationDate(pet.getCreationDate())
                .removed(true)
                .age(pet.getAge())
                .birthday(pet.getBirthday())
                .weight(pet.getWeight())
                .color(pet.getColor())
                .build();

        petRepository.save(petRemoved);

        return pet.getName();
    }
}
