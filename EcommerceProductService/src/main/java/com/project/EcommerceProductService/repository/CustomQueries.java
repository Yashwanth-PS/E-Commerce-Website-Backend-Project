package com.project.EcommerceProductService.repository;

public interface CustomQueries {
    String FIND_PRODUCT_BY_TITLE = "SELECT * FROM product WHERE title LIKE :title";
    String FIND_ALL_PRODUCTS = "SELECT * FROM product WHERE title LIKE :title AND :id";
}
