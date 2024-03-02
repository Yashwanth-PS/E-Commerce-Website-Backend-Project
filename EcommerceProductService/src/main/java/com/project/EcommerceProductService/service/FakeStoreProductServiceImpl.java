package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.client.FakeStoreAPIClient;
import com.project.EcommerceProductService.dto.*;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import com.project.EcommerceProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.EcommerceProductService.mapper.ProductMapper.productRequestToFakeStoreProductRequest;
import static com.project.EcommerceProductService.mapper.ProductMapper.fakeStoreProductResponseToProductResponse;
import static com.project.EcommerceProductService.util.ProductUtils.isNull;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder; // It will be injected Automatically
    private FakeStoreAPIClient fakeStoreAPIClient;
    private RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate;

    @Autowired // Optional - For Dependency Injection
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient,
                                       RedisTemplate<String, FakeStoreProductResponseDTO> redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOs = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO : fakeStoreProductResponseDTOs) {
            productListResponseDTO.getProducts().add(fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO));
        }
        return productListResponseDTO;
    }

    @Override
    public GenericProductDTO getProductById(int id) throws ProductNotFoundException {
        /* Check if we have the Product with given ID in the Redis Cache or not
        If we have the Product in the Redis Cache, then return the Product from the Redis Cache
        Else, call the FakeStore API to get the Product
        Store it in the Redis Cache
        Return the Product */

        // Check if we have the Product with given ID in the Redis Cache or not
        // PRODUCTS is the Table/Map Name, id is the Key
        FakeStoreProductResponseDTO fakeStoreProductResponseDTOFromCache = (FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS", id);
        if (fakeStoreProductResponseDTOFromCache != null) {
            // If we have the Product in the Redis Cache, then return the Product from the Redis Cache
            return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTOFromCache);
        }
        // Else, call the FakeStore API to get the Product
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        if (isNull(fakeStoreProductResponseDTO)) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        // Store it in the Redis Cache
        redisTemplate.opsForHash().put("PRODUCTS", id, fakeStoreProductResponseDTO);
        // Return the Product
        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public GenericProductDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestToFakeStoreProductRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductDTO = fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
        return fakeStoreProductResponseToProductResponse(fakeStoreProductDTO);
    }

    @Override
    public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updateProduct) {
        return null;
    }

    @Override
    public GenericProductDTO findProductByTitle(String title) {
        return null;
    }
}
