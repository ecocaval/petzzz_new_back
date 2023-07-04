package com.app.petz.controller;

import com.app.petz.core.dto.ProductMainInfoDto;
import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.*;
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
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductGetPutResponseJson>> findAll() {
        List<ProductGetPutResponseJson> products = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetPutResponseJson> findById(@PathVariable UUID id) {
        ProductGetPutResponseJson product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/sizes/{productId}")
    public ResponseEntity<ProductSizesGetResponseJson> findProductSizesByProductId(@PathVariable UUID productId) {
        ProductSizesGetResponseJson productSizes = productService.findProductSizesById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productSizes);
    }

    @PostMapping()
    public ResponseEntity<ProductPostResponseJson> create(
            @RequestBody @Valid ProductPostRequestJson productPostRequestJson
    ) {
        ProductPostResponseJson product = productService.create(productPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductGetPutResponseJson> replace(
            @PathVariable UUID id,
            @RequestBody ProductPutRequestJson productPutRequestJson
    ) {
        ProductGetPutResponseJson product = productService.replace(id, productPutRequestJson);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDeleteResponseJson> delete(@PathVariable UUID id) {
        ProductMainInfoDto productMainInfo = productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDeleteResponseJson(productMainInfo));
    }
}
