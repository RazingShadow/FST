package com.fst.backend.dto.mapper;

import com.fst.backend.dto.request.ProductRequest;
import com.fst.backend.dto.response.ProductResponse;
import com.fst.backend.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toResponse(ProductEntity productEntity) {
        return new ProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getCategory(),
                productEntity.getPrice()
        );
    }

    public ProductEntity toEntity(ProductRequest productRequest) {
        return new ProductEntity(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getCategory(),
                productRequest.getPrice()
        );
    }
}
