package com.fst.backend.service.impl;

import com.fst.backend.dto.exception.ProductNotFoundException;
import com.fst.backend.dto.mapper.ProductMapper;
import com.fst.backend.dto.request.ProductRequest;
import com.fst.backend.dto.response.ProductResponse;
import com.fst.backend.persistence.entity.ProductEntity;
import com.fst.backend.persistence.repository.ProductRepository;
import com.fst.backend.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productResponseMapper) {
        this.productRepository = productRepository;
        this.productMapper = productResponseMapper;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductEntity product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        applyUpdates(productEntity, request);

        return productMapper.toResponse(productRepository.save(productEntity));
    }

    private void applyUpdates(ProductEntity productEntity, ProductRequest productRequest) {

        Optional.ofNullable(productRequest.getName())
                .ifPresent(productEntity::setName);

        Optional.ofNullable(productRequest.getDescription())
                .ifPresent(productEntity::setDescription);

        Optional.ofNullable(productRequest.getCategory())
                .ifPresent(productEntity::setCategory);

        Optional.of(productRequest.getPrice())
                .ifPresent(productEntity::setPrice);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
