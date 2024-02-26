package com.project.EcommerceProductService.controller;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.dto.SearchRequestDTO;
import com.project.EcommerceProductService.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /* @PostMapping
    public List<GenericProductDTO> searchProducts(@RequestBody SearchRequestDTO requestDTO) {
        return searchService.searchProducts(requestDTO.getQuery(), requestDTO.getPageNumber(),
                requestDTO.getItemsPerPage(), requestDTO.getSortParams());
    } */

    @PostMapping
    public Page<GenericProductDTO> searchProducts(@RequestBody SearchRequestDTO requestDTO) {
        List<GenericProductDTO> genericProductDTOList = searchService.searchProducts(requestDTO.getQuery(),
                requestDTO.getPageNumber(), requestDTO.getItemsPerPage(), requestDTO.getSortParams());
        // Converting a List of GenericProductDTO to a Page of GenericProductDTO
        Page<GenericProductDTO> genericProductDTOPage = new PageImpl<>(genericProductDTOList); // Page is also a Collection
        return genericProductDTOPage; // The Order of Page Starts from 0
    } /* POST - http://localhost:8080/search */
}