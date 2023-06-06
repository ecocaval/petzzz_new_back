package com.app.petz.controller;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.model.Product;
import com.app.petz.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductPostResponseJson> createProduct(@RequestBody @Valid ProductPostRequestJson productPostRequestJson){
        Product product = productService.createProduct(productPostRequestJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToProductPostResponseJson(product));
    }

    @GetMapping("/product")
    public ResponseEntity<Page<Product>> listPageable(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.listPageable(pageable));
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAllNonPageable());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
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
