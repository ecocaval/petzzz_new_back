package com.app.petz.factory;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.model.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PetCreator {
    public static Pet createPetToBeSaved(){
        return Pet.builder()
                .name("Doguinho")
                .age(6)
                .creationDate(LocalDateTime.now())
                .removed(false)
                .color("Caramelo")
                .weight(18.0)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .mainImageUrl("http://")
                .build();
    }

    public static Pet createValidPet(){
        return Pet.builder()
                .id(UUID.fromString("b2664036-fe96-11ed-be56-0242ac120002"))
                .name("Doguinho")
                .age(6)
                .creationDate(LocalDateTime.now())
                .removed(false)
                .color("Caramelo")
                .weight(18.0)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .mainImageUrl("http://")
                .build();
    }

    public static Pet createValidUpdatedPet(){
        return Pet.builder()
                .id(UUID.fromString("b2664036-fe96-11ed-be56-0242ac120002"))
                .name("Doguinho Atualizado")
                .age(8)
                .creationDate(LocalDateTime.now())
                .removed(false)
                .color("Caramelo Claro")
                .weight(18.0)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .mainImageUrl("http://")
                .build();
    }

    public static PetPostRequestJson createPetPostRequestJson(){
        return PetPostRequestJson.builder()
                .name(PetCreator.createValidPet().getName())
                .age(PetCreator.createValidPet().getAge())
                .birthday("20/04/1999")
                .weight(PetCreator.createValidPet().getWeight())
                .color(PetCreator.createValidPet().getColor())
                .build();
    }

    public static PetPostResponseJson createPetPostResponseJson(){
        return PetPostResponseJson.builder()
                .id(UUID.fromString("b2664036-fe96-11ed-be56-0242ac120002"))
                .timeStamp(LocalDateTime.now())
                .message("O pet Doguinho da request foi criado com sucesso.")
                .build();
    }

    public static PetGetResponseJson createPetGetResponseJson() {
        return PetGetResponseJson.builder()
                .id(UUID.fromString("b2664036-fe96-11ed-be56-0242ac120002"))
                .creationDate(LocalDateTime.now())
                .name("Doguinho")
                .age(6)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .weight(18.0)
                .color("Caramelo")
                .mainImageUrl("http://")
                .build();
    }

    public static PetPutRequestJson createPetPutRequestJson() {
        return PetPutRequestJson.builder()
                .name("Doguinho Atualizado")
                .age(8)
                .birthday("28/05/2018")
                .weight(18.0)
                .color("Caramelo")
                .mainImageUrl("http://")
                .build();
    }
}
