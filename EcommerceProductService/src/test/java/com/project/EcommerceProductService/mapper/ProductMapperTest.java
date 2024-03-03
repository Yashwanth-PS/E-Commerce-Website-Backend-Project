package com.project.EcommerceProductService.mapper;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.ProductListResponseDTO;
import com.project.EcommerceProductService.model.Category;
import com.project.EcommerceProductService.model.Price;
import com.project.EcommerceProductService.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ProductMapperTest {
    @Test
    public void testConvertProductsToProductListResponseDTO(){
        // Arrange
        Category category = new Category();
        category.setCategoryName("Electronics");

        Price price = new Price();
        price.setAmount(1000);
        price.setDiscount(0);
        price.setCurrency("Rupees");

        Product product1 = new Product();
        product1.setTitle("Laptop");
        product1.setDescription("Best Laptop");
        product1.setImage("someImageURL");
        product1.setCategory(category);
        product1.setPrice(price);

        Product product2 = new Product();
        product2.setTitle("Mobile");
        product2.setDescription("Best Phone");
        product2.setImage("someImageURL");
        product2.setCategory(category);
        product2.setPrice(price);

        List<Product> products = Arrays.asList(product1, product2);

        // Act
        ProductListResponseDTO productListResponseDTO = ProductMapper.convertProductsToProductListResponseDTO(products);

        // Assert
        Assertions.assertNotNull(productListResponseDTO);
        Assertions.assertEquals(2, productListResponseDTO.getProducts().size());

        GenericProductDTO responseDTO = productListResponseDTO.getProducts().get(0);
        Assertions.assertEquals(product1.getTitle(), responseDTO.getTitle());
        Assertions.assertEquals(product1.getDescription(), responseDTO.getDescription());
        Assertions.assertEquals(product1.getImage(), responseDTO.getImage());

    }
}
