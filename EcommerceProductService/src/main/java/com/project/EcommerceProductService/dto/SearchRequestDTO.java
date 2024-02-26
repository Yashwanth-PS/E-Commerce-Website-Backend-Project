package com.project.EcommerceProductService.dto;

import com.project.EcommerceProductService.model.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query; // Title of the product
    private int pageNumber;
    private int itemsPerPage;
    // private List<String> sortParams; // ['title', 'price', 'rating']
    private List<SortParam> sortParams; // [('title', 'ASC'), ('price', 'DSC), ('rating', 'ASC)]
}