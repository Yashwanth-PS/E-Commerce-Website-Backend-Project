package com.project.EcommerceProductService.repository;

import com.project.EcommerceProductService.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository // Create a Bean
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // Auto-Generated Custom Queries
    @Override
    List<Product> findAll(); // Get all the products from the Product table.
    Product findByTitle(String title);
    Product findByTitleAndDescription(String title, String Description); // SELECT * FROM Product WHERE title = "" AND description = ""
    Product findByTitleOrDescription(String title, String Description); // SELECT * FROM Product WHERE title = "" OR description = ""

    @Override
    <S extends Product> List<S> findAll(Example<S> example);

    List<Product> findAllByPrice_ValueLessThan(Integer x);

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

    // @Query(value = "SELECT * FROM product WHERE id = 1", nativeQuery = true)
    List<Product> findAllByPrice_ValueBetween(Integer x, Integer y);

    // SELECT * FROM products WHERE LOWER(title) = "iphone" - Offset and Limit
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
