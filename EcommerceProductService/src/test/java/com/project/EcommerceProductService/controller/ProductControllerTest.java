package com.project.EcommerceProductService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.dto.ProductRequestDTO;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import com.project.EcommerceProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// Unit Tests for Controller Demo
@WebMvcTest(ProductController.class) // We need to test API calls
// @SpringBootTest
// @AutoConfigureMockMvc
public class ProductControllerTest { // Integration Test, WebMVC Test
//    @Autowired
//    private MockMvc mockMvc; // Helps to Mock and make calls to the API
//
//    @MockBean(name="productService")
//    private ProductService productService;
//
//    // Unit Test for Controllers Methods invoked via APIs
//    @Test
//    void getAllProductsReturnEmptyListWhenNoProductsAvailable() throws Exception {
//        // Arrange
//        ProductListResponseDTO emptyProductListResponse = new ProductListResponseDTO();
//        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);
//        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);
//
//        mockMvc.perform(get("/products").header("token", "token"))
//                .andExpect(status().is(200))
//                .andExpect(content().string("{\"products\":[]}"));
//    }
//
//    @Test
//    void getAllProductsReturnProducts() throws Exception {
//        // Arrange
//        ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
//        GenericProductDTO product1 = new GenericProductDTO();
//        product1.setId(UUID.fromString("9b177c33-3d5e-49aa-b285-7f43dc15bbb9"));
//        product1.setTitle("Laptop");
//        product1.setCategory("Electronics");
//        product1.setDescription("Best Laptop");
//        product1.setPrice(1000);
//        product1.setImage("someImageURL");
//
//        GenericProductDTO product2 = new GenericProductDTO();
//        product2.setId(UUID.fromString("76b67b82-ffb5-4150-bbfa-e0e983097038"));
//        product2.setTitle("SmartPhone");
//        product2.setCategory("Electronics");
//        product2.setDescription("Best Phone");
//        product2.setPrice(2000);
//        product2.setImage("someImageURL");
//
//        productListResponseDTO.setProducts(List.of(product1, product2));
//
//        when(productService.getAllProducts()).thenReturn(productListResponseDTO);
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().is(200))
//                .andExpect(content().string("{\"products\":[{\"id\":\"9b177c33-3d5e-49aa-b285-7f43dc15bbb9\",\"title\":\"Laptop\",\"price\":1000.0,\"category\":\"Electronics\",\"description\":\"Best Laptop\",\"image\":\"someImageURL\"},{\"id\":\"76b67b82-ffb5-4150-bbfa-e0e983097038\",\"title\":\"SmartPhone\",\"price\":2000.0,\"category\":\"Electronics\",\"description\":\"Best Phone\",\"image\":\"someImageURL\"}]}"));
//    }
//
//    @Test
//    void createProductSuccess() throws Exception {
//        // Arrange
//        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
//        productRequestDTO.setTitle("Laptop");
//        productRequestDTO.setCategory("Electronics");
//        productRequestDTO.setDescription("Best Laptop");
//        productRequestDTO.setPrice(1000);
//        productRequestDTO.setImage("someImageURL");
//
//        GenericProductDTO productResponseDTO = new GenericProductDTO();
//        productResponseDTO.setId(UUID.fromString("76b67b82-ffb5-4150-bbfa-e0e983097038"));
//        productResponseDTO.setTitle("Laptop");
//        productResponseDTO.setCategory("Electronics");
//        productResponseDTO.setDescription("Best Laptop");
//        productResponseDTO.setPrice(1000);
//        productResponseDTO.setImage("someImageURL");
//
//        String requestJson = convertToJson(productRequestDTO);
//        String responseJson = convertToJson(productResponseDTO);
//
//        // when(productService.createProduct(any())).thenReturn(productResponseDTO);
//        when(productService.createProduct(eq(productRequestDTO))).thenReturn(productResponseDTO); // eq - Equivalent Object, Compare the Object not the Reference
//
//        mockMvc.perform(post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestJson)) // We can also pass other parameters in the path or in the headers
//                .andExpect(status().is(200))
//                .andExpect(content().string(responseJson));
//    }
//
//    @Test
//    void deleteProductByIdSuccess() throws Exception {
//        when(productService.deleteProduct(5)).thenReturn(true); // eq - Equivalent Object, Compare the Object not the Reference
//
//        mockMvc.perform(delete("/products/5"))
//                .andExpect(status().is(200))
//                .andExpect(content().string("true"));
//    }
//
//    @Test
//    void findProductByIdFailure() throws Exception {
//        when(productService.getProductById(1)).thenThrow(new ProductNotFoundException("Product Not Found"));
//        mockMvc.perform(get("/products/1"))
//                .andExpect(status().is(404))
//                .andExpect(content().string("{\"message\":\"Product Not Found\",\"messageCode\":404}"));
//    }
//
//    @Test
//    void findProductByIdSuccess() throws Exception {
//        GenericProductDTO productResponseDTO = new GenericProductDTO();
//        productResponseDTO.setId(UUID.fromString("76b67b82-ffb5-4150-bbfa-e0e983097038"));
//        productResponseDTO.setTitle("Laptop");
//        productResponseDTO.setCategory("Electronics");
//        productResponseDTO.setDescription("Best Laptop");
//        productResponseDTO.setPrice(1000);
//        productResponseDTO.setImage("someImageURL");
//
//        String respString = convertToJson(productResponseDTO);
//
//        when(productService.getProductById(1)).thenReturn(productResponseDTO);
//        mockMvc.perform(get("/products/1"))
//                .andExpect(status().is(200))
//                .andExpect(content().string(respString));
//    }
//
//    private String convertToJson(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
}
