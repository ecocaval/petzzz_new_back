package com.app.petz.repository;

import com.app.petz.model.Pet;
import com.app.petz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    @Query(value = "SELECT * FROM pet WHERE removed = false ORDER BY creation_date DESC", nativeQuery = true)
    List<Pet> findAllNotRemoved();

    List<Pet> findByName(String name);
}