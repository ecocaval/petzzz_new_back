package com.app.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<com.app.petz.model.Customer, UUID> {
    Optional<com.app.petz.model.Customer> findByEmail(String email);
}
