package com.project.EcommerceProductService.repository;

import com.project.EcommerceProductService.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository // Create a Bean
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // Auto-Generated Custom Queries
    Product findByTitle(String title);
    Product findByTitleAndDescription(String title, String Description); // SELECT * FROM Product WHERE title = "" AND description = ""
    Product findByTitleOrDescription(String title, String Description); // SELECT * FROM Product WHERE title = "" OR description = ""

    /* Product findByPrice_AmountLessThan(double price); // < price
    Product findByPrice_AmountLessThanEqual(double price); // <= price
    Product findByPrice_AmountGreaterThan(double price); // > price
    Product findByPrice_AmountGreaterThanEqual(double price); // >= price
    List<Product> findByPriceBetweenStartPriceAndEndPrice(double startPrice, double endPrice); */

    // Custom SQL Queries in JPA
    @Query(value = CustomQueries.FIND_PRODUCT_BY_TITLE, nativeQuery = true)
    Product findProductByTitleLike(String title);

    @Query(value = CustomQueries.FIND_ALL_PRODUCTS, nativeQuery = true)
    Product findAllProducts(String title, UUID id);

    // SELECT * FROM products WHERE LOWER(title) = "iphone" - Offset and Limit
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
