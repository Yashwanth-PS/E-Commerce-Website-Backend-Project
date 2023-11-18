package com.project.EcommerceProductService.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data // @Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor
@Entity // Should have Id Attribute
public class Price extends BaseModel{ // Price : Product --> 1:1 Cardinality
    private String currency;
    private double amount;
    private double discount;
}
