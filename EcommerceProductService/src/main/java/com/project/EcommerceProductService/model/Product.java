package com.project.EcommerceProductService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity // We can specify the table name (name = "ECOM_PRODUCT")
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne
    // @JoinColumn(name = "category_id") // The output will be same even if @JoinColumn is added
    private Category category;
    @OneToOne
    private Price price;
    // @ManyToMany(mappedBy = "products") // mapped By avoids two mapping tables
    // private List<Order> orders; // Table name is ECOM_PRODUCT_ORDERS
}

/* Cardinality:
Product - Category = M : 1 --> UniDirectional Mapping
   1          1
   M          1
   M          1

Product - Price = 1 : 1 */