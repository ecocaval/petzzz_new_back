package com.app.petz.service;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.exception.PetNotFoundException;
import com.app.petz.model.Pet;
import com.app.petz.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<PetGetResponseJson> findAll() {
        return petRepository.findAllNotRemoved()
                .stream()
                .map(Pet::toGetResponseJson)
                .toList();
    }

    public PetGetResponseJson findById(UUID id) {
        Pet pet = checkPetExistence(id);

        return Pet.toGetResponseJson(pet);
    }

    public Pet checkPetExistence(UUID id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException("O pet solicitado não foi encontrado!"));
        if(pet.getRemoved()) throw new PetNotFoundException("O pet solicitado foi deletado!");

        return pet;
    }

    @Transactional
    public PetPostResponseJson create(PetPostRequestJson petPostRequestJson) {
        Pet pet = Pet.fromCreateRequest(petPostRequestJson, birthdayFormatter);
        return Pet.toPostResponseJson(petRepository.save(pet));
    }

    @Transactional
    public PetGetResponseJson replace(UUID id, PetPutRequestJson petPutRequestJson) {
        Pet pet = checkPetExistence(id);

        Pet petUpdated = Pet.fromPutRequest(pet, petPutRequestJson, birthdayFormatter);

        petRepository.save(petUpdated);

        return Pet.toGetResponseJson(checkPetExistence(id));
    }

    @Transactional
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
