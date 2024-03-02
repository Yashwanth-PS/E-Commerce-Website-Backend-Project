package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import com.project.EcommerceProductService.model.Product;

public interface ProductService {
    ProductListResponseDTO getAllProducts();
    GenericProductDTO getProductById(int id) throws ProductNotFoundException;
    GenericProductDTO createProduct(ProductRequestDTO productRequestDTO);
    boolean deleteProduct(int id);
    Product updateProduct(int id, Product updateProduct);
    GenericProductDTO findProductByTitle(String title);
}
