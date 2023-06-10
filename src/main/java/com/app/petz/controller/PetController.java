package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetDeleteResponseJson;
import com.app.petz.core.responses.PetGetPutResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PetController {
    private final PetMapper petMapper;
    private final PetService petService;

    @PostMapping("/pet")
    public ResponseEntity<PetPostResponseJson> createPet(
            @RequestBody @Valid PetPostRequestJson petPostRequestJson
    ) {
        Pet pet = petService.createPet(petPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(petMapper.petToResponseJson(pet));
    }

    @GetMapping("/pet/all")
    public ResponseEntity<List<PetGetPutResponseJson>> listAll(){
        var pets = petService.listAll()
                             .stream()
                             .map(petMapper::petToGetPutResponseJson)
                             .toList();
        return ResponseEntity.status(HttpStatus.OK)
                             .body(pets);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<PetGetPutResponseJson> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                             .body(petService.findById(id));
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity<PetGetPutResponseJson> replacePet(
            @PathVariable UUID id,
            @RequestBody PetPutRequestJson petPutRequestJson)
    {
        PetGetPutResponseJson pet = petService.replacePet(id, petPutRequestJson);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(pet);
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<PetDeleteResponseJson> deletePet(@PathVariable UUID id){
        String petName = petService.deletePet(id);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new PetDeleteResponseJson(petName));
    }

}
