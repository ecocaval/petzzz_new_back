package com.app.petz.controller;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.model.Product;
import com.app.petz.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.ReflectPermission;

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
        return new ResponseEntity<>(productMapper.productToProductResponseJson(product), HttpStatus.CREATED);
    }
}
