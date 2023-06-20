package com.app.petz.repository;

import com.app.petz.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, UUID> {

    @Query(value = "" +
            "SELECT * " +
            "FROM product_size " +
            "WHERE removed = false AND product_id = :productId " +
            "ORDER BY creation_date DESC", nativeQuery = true)
    List<ProductSize> findAllByProductId(@Param("productId") UUID productId);
}