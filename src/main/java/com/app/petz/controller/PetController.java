package com.app.petz.controller;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.CreatePetRequestJson;
import com.app.petz.core.responses.CreatePetResponseJson;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PetController {

    private final PetMapper petMapper;
    private final PetService petService;

    public PetController(PetMapper petMapper, PetService petService) {
        this.petMapper = petMapper;
        this.petService = petService;
    }

    @PostMapping("/pet")
    public ResponseEntity<CreatePetResponseJson> createPet(
            @RequestBody @Valid CreatePetRequestJson createPetRequestJson
    ) {

        PetCoreDto petCoreDto = petMapper.createRequestToPetDto(createPetRequestJson);

        Pet pet = petService.createPet(petCoreDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                              .body(petMapper.petToResponseJson(pet));
    }

}
