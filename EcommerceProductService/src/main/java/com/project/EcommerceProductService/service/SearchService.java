package com.project.EcommerceProductService.service;

import com.project.EcommerceProductService.dto.GenericProductDTO;
import com.project.EcommerceProductService.model.Product;
import com.project.EcommerceProductService.model.SortParam;
import com.project.EcommerceProductService.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.EcommerceProductService.mapper.ProductMapper.convertProductToGenericProductDTO;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    // private OpenSearchProductRepository openSearchProductRepository;

    public SearchService(ProductRepository productRepository) { // OpenSearchProductRepository openSearchProductRepository
        this.productRepository = productRepository;
        // this.openSearchProductRepository = openSearchProductRepository;
    }

    public List<GenericProductDTO> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams) { // The Parameters to the Service should not be a DTO
        /* Sort sort = Sort.by("title").ascending()
                        .and(Sort.by("rating").descending())
                        .and(Sort.by("price").descending()
                        .and(Sort.by("delivery_time").ascending()); */
        Sort sort = null;
        if (sortParams.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        } else {
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }
        for (int i = 1; i < sortParams.size(); i++) { // Need to Fix this For Loop for Multiple Sort Parameters
            if (sortParams.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortParamName())).ascending();
            } else {
                sort = sort.and(Sort.by(sortParams.get(i).getSortParamName())).descending();
            }
        }
        // Page Request is an Object/Type of Pageable Interface
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        List<Product> products = productRepository.findAllByTitleContainingIgnoreCase(query, pageRequest);
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for (Product product : products) { // Convert the List of Products to a List of GenericProductDTO
            genericProductDTOS.add(convertProductToGenericProductDTO(product));
        }
        return genericProductDTOS;
    }
}
/* Search the Products with the Title "iPhone" and then
Sort them by Price in Ascending Order and inventory in Descending Order.
SELECT *
FROM products
WHERE LOWER(title) LIKE '%iphone%'
ORDER BY price, inventory DESC; */