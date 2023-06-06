package com.app.petz.service;

import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.exception.ProductNotFoundException;
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

    public Product findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        if(product.getRemoved()) throw new ProductNotFoundException("Product deleted!");
        return product;
    }

    public void replaceProduct(UUID id, ProductPutRequestJson productPutRequestJson) {
        Product productFinded = findById(id);

        Product productUpdated = productMapper.productPutRequestToProduct(productFinded, productPutRequestJson);

        productRepository.save(productUpdated);
    }

    public void deleteProduct(UUID id) {
        Product productFinded = findById(id);

        Product productRemoved = Product.builder()
                .id(productFinded.getId())
                .removed(true)
                .creationDate(productFinded.getCreationDate())
                .name(productFinded.getName())
                .description(productFinded.getDescription())
                .sku(productFinded.getSku())
                .mainImageUrl(productFinded.getMainImageUrl())
                .hasLocalAcquire(productFinded.getHasLocalAcquire())
                .build();

        productRepository.save(productRemoved);
    }
}
