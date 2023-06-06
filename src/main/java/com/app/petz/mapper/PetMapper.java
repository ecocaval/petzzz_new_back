package com.app.petz.mapper;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetPutResponseJson;
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
                .name(createRequest.name())
                .age(createRequest.age())
                .creationDate(LocalDateTime.now())
                .removed(false)
                .birthday(LocalDate.parse(createRequest.birthday(), birthdayFormatter))
                .weight(createRequest.weight())
                .color(createRequest.color())
                .build();
    }

    public Pet petDtoToPet(PetCoreDto petCoreDto) {
        return Pet.builder()
                .id(petCoreDto.id())
                .removed(false)
                .creationDate(LocalDateTime.now())
                .name(petCoreDto.name())
                .age(petCoreDto.age())
                .birthday(petCoreDto.birthday())
                .weight(petCoreDto.weight())
                .color(petCoreDto.color())
                .build();
    }

    public PetPostResponseJson petToResponseJson(Pet pet) {
        return PetPostResponseJson.builder()
                .petUuid(pet.getId())
                .message("O pet " + pet.getName() + " foi criado com sucesso.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public PetGetPutResponseJson petToGetPutResponseJson(Pet pet){
        return PetGetPutResponseJson.builder()
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
                .name(petPutRequestJson.name() != null ? petPutRequestJson.name() : pet.getName())
                .age(petPutRequestJson.age() != null ? petPutRequestJson.age() : pet.getAge())
                .birthday(petPutRequestJson.birthday() != null ? LocalDate.parse(petPutRequestJson.birthday(), birthdayFormatter) : pet.getBirthday())
                .weight(petPutRequestJson.weight() != null ? petPutRequestJson.weight() : pet.getWeight())
                .color(petPutRequestJson.color() != null ? petPutRequestJson.color() : pet.getColor())
                .mainImageUrl(petPutRequestJson.mainImageUrl() != null ? petPutRequestJson.mainImageUrl() : pet.getMainImageUrl())
                .build();
    }
}
