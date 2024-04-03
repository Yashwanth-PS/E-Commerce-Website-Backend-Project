package com.project.EcommerceProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "ECOM_ORDER")
public class Order extends BaseModel{
    private double price;
    @ManyToMany
    @JoinTable(name = "products_orders", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products; // ECOM_ORDER_PRODUCTS
}

/* Product - Order -> M : M --> Uni-Directional
      1        M
      M        1

Use Case: We don't want the List of Orders in which the
Product is present when we are just fetching the Products */