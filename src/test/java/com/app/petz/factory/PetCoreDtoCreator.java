package com.app.petz.factory;

import com.app.petz.core.dto.PetCoreDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PetCoreDtoCreator {
    public static PetCoreDto createPetCoreDto(){
        return PetCoreDto.builder()
                .name("Doguinho")
                .age(6)
                .color("Caramelo")
                .weight(18.0)
                .birthday(LocalDate.parse("28/03/2018", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
