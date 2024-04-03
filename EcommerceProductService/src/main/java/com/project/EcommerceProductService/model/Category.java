package com.project.EcommerceProductService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity //  We can specify the table name (name = "CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel{
    @Column(nullable = false)
    private String categoryName;
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "category") // The mapped By name should be the same as the variable name in Product class
    @JoinColumn(name = "category_id") // Adds a column (category_id) in Product Table
    private List<Product> products;
}

/* Group
    - Creator
    - Admin
    - Members

Student ----- Batch

    1             1
Student -------- Batch (current batch) => M:1
   M                1

   1              M
Student -------- Batch (previous batch) => M:M
   M               1

    - current batch
    - previous batch */

// Category ------ Product