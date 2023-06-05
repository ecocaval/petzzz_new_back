package com.app.petz.service;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.model.Product;
import com.app.petz.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    public Product createProduct(ProductPostRequestJson productPostRequestJson) {
        Product product = productMapper.productPostRequestJsonToProduct(productPostRequestJson);
        return productRepository.save(product);
    }

    public Page<Product> listPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> listAllNonPageable(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }
}
