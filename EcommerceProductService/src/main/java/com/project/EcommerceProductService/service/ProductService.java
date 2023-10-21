package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductResponseDTO;
import com.project.EcommerceProductService.model.Product;

public interface ProductService {
    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id);
    Product createProduct(Product product);
    Product deleteProduct(int id);
    Product updateProduct(int id, Product updateProduct);
}
