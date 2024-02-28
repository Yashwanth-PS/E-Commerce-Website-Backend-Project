package com.project.EcommerceProductService.mapper;

import com.project.EcommerceProductService.dto.*;
import com.project.EcommerceProductService.model.Product;

import java.util.List;

public class ProductMapper {
    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO) { // Classes used for conversion of classes
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        return fakeStoreProductRequestDTO;
    }

    public static GenericProductDTO fakeStoreProductResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO) {
        GenericProductDTO productResponseDTO = new GenericProductDTO();
        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        return productResponseDTO;
    }

    public static ProductListResponseDTO convertProductsToProductListResponseDTO(List<Product> products) {
        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
        for (Product p : products) {
            GenericProductDTO productResponseDTO = createGenericProductDTO(p);
            productListResponseDTO.getProducts().add(productResponseDTO);
        }
        return productListResponseDTO;
    }

    private static GenericProductDTO createGenericProductDTO(Product p) {
        GenericProductDTO productResponseDTO = new GenericProductDTO();
        productResponseDTO.setId(p.getUuid());
        productResponseDTO.setImage(p.getImage());
        productResponseDTO.setTitle(p.getTitle());
        productResponseDTO.setPrice(p.getPrice().getAmount());
        productResponseDTO.setDescription(p.getDescription());
        productResponseDTO.setCategory(p.getCategory().getCategoryName());
        return productResponseDTO;
    }

    public static GenericProductDTO convertProductToGenericProductDTO(Product p) {
        return createGenericProductDTO(p);
    }
}