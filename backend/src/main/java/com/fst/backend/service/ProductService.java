package com.fst.backend.service;

import com.fst.backend.dto.request.ProductRequest;
import com.fst.backend.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(Long id, ProductRequest request);
    ProductResponse getProductById(Long id);
    void deleteProductById(Long id);
    List<ProductResponse> getAllProducts();
}
