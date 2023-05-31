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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return new ResponseEntity<>(petMapper.petToResponseJson(pet), HttpStatus.CREATED);
    }

    @GetMapping("/pet/all")
    public ResponseEntity<List<Pet>> listAll(){
        return new ResponseEntity<>(petService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<PetGetResponseJson> findById(@PathVariable UUID id){
        return new ResponseEntity<>(petService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/pet/{id}")
    public ResponseEntity<Void> replacePet(@PathVariable UUID id, @RequestBody PetPutRequestJson petPutRequestJson){
        petService.replacePet(id, petPutRequestJson);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<Void>  deletePet(@PathVariable UUID id){
        petService.deletePet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
