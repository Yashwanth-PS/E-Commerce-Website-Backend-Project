package com.project.EcommerceProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDTO {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
