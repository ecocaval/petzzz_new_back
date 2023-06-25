package com.app.petz.controller;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetDeleteResponseJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
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
@RequestMapping("/api/v1/pet")
public class PetController {

    private final PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<PetGetResponseJson>> findAll() {
        var pets = petService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetGetResponseJson> findById(@PathVariable UUID id) {
        var pet = petService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @PostMapping
    public ResponseEntity<PetPostResponseJson> create(
            @RequestBody @Valid PetPostRequestJson petPostRequestJson
    ) {
        var pet = petService.create(petPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetGetResponseJson> replace(
            @PathVariable UUID id,
            @RequestBody PetPutRequestJson petPutRequestJson) {
        var pet = petService.replace(id, petPutRequestJson);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PetDeleteResponseJson> delete(@PathVariable UUID id) {
        String petName = petService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new PetDeleteResponseJson(petName));
    }
}
