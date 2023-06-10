package com.app.petz.controller;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductGetResponseJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.model.Product;
import com.app.petz.service.ProductService;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductPostResponseJson> createProduct(
            @RequestBody @Valid ProductPostRequestJson productPostRequestJson
    ){
        Product product = productService.createProduct(productPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToProductPostResponseJson(product));
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductGetResponseJson>> findAll(){
        List<ProductGetResponseJson> products = productService.findAll()
                .stream()
                .map(productMapper::productToProductGetResponseJson)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductGetResponseJson> findById(@PathVariable UUID id){
        ProductGetResponseJson product = productMapper.productToProductGetResponseJson(productService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Void> replaceProduct(@PathVariable UUID id, @RequestBody ProductPutRequestJson productPutRequestJson){
        productService.replaceProduct(id ,productPutRequestJson);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
