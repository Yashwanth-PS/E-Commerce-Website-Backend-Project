package com.project.EcommerceProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity //  We can specify the table name (name = "CATEGORY")
public class Category extends BaseModel{
    private String categoryName;
    /* @OneToMany(mappedBy = "category") // The mapped By name should be the same as the variable name in Product class
    // @JoinColumn(name = "category_id") // Adds a column in Product Table
    private List<Product> products; */
}
