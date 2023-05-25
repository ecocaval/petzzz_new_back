package com.app.petz.service;

import com.app.petz.core.dto.PetCoreDto;
import com.app.petz.mapper.PetMapper;
import com.app.petz.model.Pet;
import com.app.petz.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetMapper petMapper;

    private final PetRepository petRepository;

    public PetService(PetMapper petMapper, PetRepository petRepository) {
        this.petMapper = petMapper;
        this.petRepository = petRepository;
    }

    public Pet createPet(PetCoreDto petCoreDto) {
        Pet pet = petMapper.petDtoToPet(petCoreDto);
        return petRepository.save(pet);
    }
}
