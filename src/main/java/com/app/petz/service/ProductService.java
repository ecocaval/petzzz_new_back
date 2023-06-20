package com.app.petz.service;

import com.app.petz.core.dto.ProductMainInfoDto;
import com.app.petz.core.requests.ProductPostRequestJson;
import com.app.petz.core.requests.ProductPutRequestJson;
import com.app.petz.core.responses.ProductGetPutResponseJson;
import com.app.petz.core.responses.ProductPostResponseJson;
import com.app.petz.core.responses.ProductSizesGetResponseJson;
import com.app.petz.enums.ProductSizes;
import com.app.petz.exception.ProductNotFoundException;
import com.app.petz.mapper.ProductMapper;
import com.app.petz.mapper.ProductSizeMapper;
import com.app.petz.model.Product;
import com.app.petz.model.ProductSize;
import com.app.petz.repository.ProductRepository;
import com.app.petz.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    private final ProductSizeMapper productSizeMapper;
    private final ProductSizeRepository productSizeRepository;

    public List<ProductGetPutResponseJson> findAll() {
        return productRepository.findAllNotRemoved()
                .stream()
                .map(productMapper::productToGetPutResponseJson)
                .toList();
    }

    public ProductGetPutResponseJson findById(UUID id) {
        Product product = checkProductExistenceById(id);

        return productMapper.productToGetPutResponseJson(product);
    }

    public ProductSizesGetResponseJson findProductSizesById(UUID id) {
        List<ProductSize> productSizes = productSizeRepository.findAllByProductId(id);

        if(productSizes.isEmpty()) {
            throw new ProductNotFoundException("O produto solicitado não possui tamanhos cadastrados!");
        }

        return productSizeMapper.productModelToGetResponseJson(productSizes);
    }

    public Product checkProductExistenceById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("O produto solicitado não foi encontrado!"));

        if (product.getRemoved()) throw new ProductNotFoundException("O produto solicitado foi deletado!");

        return product;
    }

    @Transactional
    public ProductPostResponseJson create(ProductPostRequestJson productPostRequestJson) {

        Product productRequest = productMapper.postRequestJsonToProduct(productPostRequestJson);

        productRepository.save(productRequest);

        if(productPostRequestJson.sizes() != null) {
            String[] productSizes = productPostRequestJson.sizes().split(",");

            // Saves a productSize entity in DB for all productSizes
            for (String size : productSizes) {

                ProductSize productSize = ProductSize.builder()
                        .removed(false)
                        .product(productRequest)
                        .size(productSizeMapper.mapStringToProductSize(size))
                        .creationDate(LocalDateTime.now())
                        .build();

                productSizeRepository.save(productSize);
            }

        } else {

            ProductSize productSize = ProductSize.builder()
                    .removed(false)
                    .product(productRequest)
                    .size(ProductSizes.M) // Size default value is M
                    .creationDate(LocalDateTime.now())
                    .build();

            productSizeRepository.save(productSize);
        }

        return productMapper.productToPostResponseJson(productRequest);
    }

    @Transactional
    public ProductGetPutResponseJson replace(UUID id, ProductPutRequestJson productPutRequestJson) {
        Product product = checkProductExistenceById(id);

        Product productUpdated = productMapper.productPutRequestToProduct(product, productPutRequestJson);

        productRepository.save(productUpdated);

        return productMapper.productToGetPutResponseJson(checkProductExistenceById(id));
    }

    @Transactional
    public ProductMainInfoDto delete(UUID id) {
        Product product = checkProductExistenceById(id);

        Product productRemoved = Product.builder()
                .id(product.getId())
                .removed(true)
                .creationDate(product.getCreationDate())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .mainImageUrl(product.getMainImageUrl())
                .hasLocalAcquire(product.getHasLocalAcquire())
                .build();

        productRepository.save(productRemoved);

        return productMapper.productToMainInfoDto(product);
    }
}