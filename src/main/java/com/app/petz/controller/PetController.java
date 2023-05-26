package com.app.petz.controller;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.service.PetService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
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
    public ResponseEntity<PetPostResponseJson> createPet(
            @RequestBody @Valid PetPostRequestJson petPostRequestJson
    ) {

        PetCoreDto petCoreDto = petMapper.createRequestToPetDto(petPostRequestJson);

        Pet pet = petService.createPet(petCoreDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                              .body(petMapper.petToResponseJson(pet));
    }

    @GetMapping("/pet/all")
    public ResponseEntity<List<Pet>> listAll(){
        List<Pet> pets = petService.listAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(pets);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<PetGetResponseJson> findById(@PathVariable UUID id){
        PetGetResponseJson pet = petService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pet);
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id, @RequestBody PetPutRequestJson petPutRequestJson){
        petService.replacePet(id, petPutRequestJson);
        log.info(petPutRequestJson.getBirthday());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
