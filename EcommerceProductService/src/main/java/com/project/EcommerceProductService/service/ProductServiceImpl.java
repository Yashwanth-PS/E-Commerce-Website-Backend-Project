package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.mapper.ProductMapper;
import com.project.EcommerceProductService.model.Product;
import com.project.EcommerceProductService.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

// @Primary
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> products = productRepository.findAll();
        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductsToProductListResponseDTO(products);
        return productListResponseDTO;
    }

    @Override
    public GenericProductDTO getProductById(int id) {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        return null;
    }

    @Override
    public GenericProductDTO findProductByTitle(String title) {
        // fingAll() -> Gives List of all Products
        // findById() -> Gives Product by ProductId
        Product product = productRepository.findByTitle(title);
        GenericProductDTO responseDTO = ProductMapper.convertProductToGenericProductDTO(product);
        return responseDTO;
    }
}
