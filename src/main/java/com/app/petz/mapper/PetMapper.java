package com.app.petz.mapper;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.CreatePetRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.model.Pet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PetMapper {
    public PetCoreDto petToPetDto(Pet pet) {
        return PetCoreDto.builder()
                .id(pet.getId())
                .creationDate(pet.getCreationDate())
                .name(pet.getName())
                .age(pet.getAge())
                .birthday(pet.getBirthday())
                .weight(pet.getWeight())
                .color(pet.getColor())
                .build();
    }

    public PetCoreDto createRequestToPetDto(CreatePetRequestJson createRequest) {
        return PetCoreDto.builder()
                .name(createRequest.getName())
                .age(createRequest.getAge())
                .birthday(createRequest.getBirthday())
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
                .petUuid(pet.getId())
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


}
