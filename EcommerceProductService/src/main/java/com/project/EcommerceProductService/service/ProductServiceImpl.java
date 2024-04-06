package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.dto.UserDTO;
import com.project.EcommerceProductService.mapper.ProductMapper;
import com.project.EcommerceProductService.model.Product;
import com.project.EcommerceProductService.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// @Primary
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private RestTemplate restTemplate;
    public ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> products = productRepository.findAll();
        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductsToProductListResponseDTO(products);
        return productListResponseDTO;
    }

    @Override
    public GenericProductDTO getProductById(int id) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        System.out.println("Request received to fetch product with id: " + id);
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/users/1", UserDTO.class);
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://userservice/users/1", UserDTO.class);
        return genericProductDTO;
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
