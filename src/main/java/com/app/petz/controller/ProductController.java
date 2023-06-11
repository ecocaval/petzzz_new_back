package com.app.petz.controller;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.PetDeleteResponseJson;
import com.app.petz.core.responses.ProductDeleteResponseJson;
import com.app.petz.core.responses.ProductGetPutResponseJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductGetPutResponseJson>> findAll() {
        var products = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductGetPutResponseJson> findById(@PathVariable UUID id) {
        var product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductPostResponseJson> create(
            @RequestBody @Valid ProductPostRequestJson productPostRequestJson
    ) {
        var product = productService.create(productPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductGetPutResponseJson> replace(
            @PathVariable UUID id,
            @RequestBody ProductPutRequestJson productPutRequestJson
    ) {
        var product = productService.replace(id, productPutRequestJson);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductDeleteResponseJson> delete(@PathVariable UUID id) {
        var productMainInfo = productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDeleteResponseJson(productMainInfo));
    }
}
