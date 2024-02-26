package com.project.EcommerceProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponseDTO {

    private List<GenericProductDTO> products;

    public ProductListResponseDTO() {
        this.products = new ArrayList<>();
    }

}
