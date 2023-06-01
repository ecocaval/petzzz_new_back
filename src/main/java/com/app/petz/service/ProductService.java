package com.app.petz.service;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.model.Product;
import com.app.petz.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
}
