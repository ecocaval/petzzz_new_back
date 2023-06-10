package com.app.petz.repository;

import com.app.petz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM product WHERE removed = false", nativeQuery = true)
    List<Product> findAllNotRemoved();

    List<Product> findByName(String name);
}
