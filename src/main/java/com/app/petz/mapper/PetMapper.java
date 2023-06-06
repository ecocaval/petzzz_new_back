package com.app.petz.mapper;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.model.Pet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PetMapper {
    private final DateTimeFormatter birthdayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Pet createRequestToPet(PetPostRequestJson createRequest) {
        return Pet.builder()
                .name(createRequest.getName())
                .age(createRequest.getAge())
                .creationDate(LocalDateTime.now())
                .removed(false)
                .birthday(LocalDate.parse(createRequest.getBirthday(), birthdayFormatter))
                .weight(createRequest.getWeight())
                .color(createRequest.getColor())
                .build();
    }

    public Pet petDtoToPet(PetCoreDto petCoreDto) {
        return Pet.builder()
                .id(petCoreDto.getId())
                .removed(false)
                .creationDate(LocalDateTime.now())
                .name(petCoreDto.getName())
                .age(petCoreDto.getAge())
                .birthday(petCoreDto.getBirthday())
                .weight(petCoreDto.getWeight())
                .color(petCoreDto.getColor())
                .build();
    }

    public PetPostResponseJson petToResponseJson(Pet pet) {
        return PetPostResponseJson.builder()
                .id(pet.getId())
                .message("O pet " + pet.getName() + " foi criado com sucesso.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public PetGetResponseJson petToGetResponseJson(Pet pet){
        return PetGetResponseJson.builder()
                .id(pet.getId())
                .creationDate(pet.getCreationDate())
                .name(pet.getName())
                .age(pet.getAge())
                .birthday(pet.getBirthday())
                .weight(pet.getWeight())
                .color(pet.getColor())
                .mainImageUrl(pet.getMainImageUrl())
                .build();

    }

    public Pet petPutRequestToPet(Pet pet, PetPutRequestJson petPutRequestJson){
        return Pet.builder()
                .id(pet.getId())
                .removed(pet.getRemoved())
                .creationDate(pet.getCreationDate())
                .name(petPutRequestJson.getName() != null ? petPutRequestJson.getName() : pet.getName())
                .age(petPutRequestJson.getAge() != null ? petPutRequestJson.getAge() : pet.getAge())
                .birthday(petPutRequestJson.getBirthday() != null ? LocalDate.parse(petPutRequestJson.getBirthday(), birthdayFormatter) : pet.getBirthday())
                .weight(petPutRequestJson.getWeight() != null ? petPutRequestJson.getWeight() : pet.getWeight())
                .color(petPutRequestJson.getColor() != null ? petPutRequestJson.getColor() : pet.getColor())
                .mainImageUrl(petPutRequestJson.getMainImageUrl() != null ? petPutRequestJson.getMainImageUrl() : pet.getMainImageUrl())
                .build();
    }


}
