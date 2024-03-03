package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.exception.InvalidTitleException;
import com.project.EcommerceProductService.exception.ProductNotFoundException;
import com.project.EcommerceProductService.model.Category;
import com.project.EcommerceProductService.model.Price;
import com.project.EcommerceProductService.model.Product;
import com.project.EcommerceProductService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any; // Not recommended to use this

import java.util.UUID;

public class ProductServiceImplTest {
    @Mock // Marking the given attribute as an object to be mocked
    private ProductRepository productRepository;

    @InjectMocks // Indicates the class that we want to test, the class where we would inject the mock object
    private ProductServiceImpl productServiceImpl;

    @BeforeEach // Executes before Each Method
    public void setup(){ // For every test case it will create a fresh mock object
        MockitoAnnotations.openMocks(this); // And inject in our test class
    } // Also creates auto closable resources for each test method

    @Test
    public void testFindProductByTitleSuccess() throws ProductNotFoundException {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = "testProductTitle";

        Product mockProduct = new Product();
        mockProduct.setUuid(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        // Act
        GenericProductDTO actualResponse = productServiceImpl.findProductByTitle(testTitle);

        // Assert
        Assertions.assertEquals(actualResponse.getId(), mockProduct.getUuid());
        Assertions.assertEquals(actualResponse.getTitle(), mockProduct.getTitle());
        Assertions.assertEquals(actualResponse.getDescription(), mockProduct.getDescription());
        Assertions.assertEquals(actualResponse.getPrice(), mockProduct.getPrice().getAmount());
    }

    @Test
    public void testFindByProductByTitle_RespondsWithNullObject() throws ProductNotFoundException {
        // Arrange
        String testTitle = "testProductTitle";
        when(productRepository.findByTitle(testTitle)).thenReturn(null);

        // GenericProductDTO actualResponse = productServiceImpl.findProductByTitle(testTitle);
        // Assertions.assertEquals(actualResponse.getTitle(), testTitle);
        Assertions.assertThrows(ProductNotFoundException.class, () -> productServiceImpl.findProductByTitle(testTitle));
    }

    @Test
    public void testFindByProductByTitle_NullTitle() throws ProductNotFoundException {
        // Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = null;

        Product mockProduct = new Product();
        mockProduct.setUuid(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);

        // Assert and Act
        Assertions.assertThrows(InvalidTitleException.class, () -> productServiceImpl.findProductByTitle(testTitle)); // Lambda Expression: Passing a function call as an argument to the Method
    }
}
