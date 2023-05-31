package com.app.petz.factory;

import com.app.petz.model.Pet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static Pet createPetToBeUpdated(){
        return Pet.builder()
                .name("Doguinho Atualizado")
                .age(7)
                .color("Azul")
                .weight(11.0)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .mainImageUrl("http://")
                .build();
    }

}
