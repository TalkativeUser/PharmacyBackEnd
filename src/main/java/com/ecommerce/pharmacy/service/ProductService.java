package com.ecommerce.pharmacy.service;


import com.ecommerce.pharmacy.DTO.ProductDTO;
import com.ecommerce.pharmacy.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product findProductById(Long Id) throws Exception;

    List<Product> getProductByCategoryId(Long categoryId) throws Exception;

    public Page<Product> findProductsWithPaginationAndSorting(int offset, String field);

    public void postProduct(ProductDTO productDTO);

    public void postListOfProducts(List<ProductDTO> productDTOList);
    public Product updateProduct(Long id, Product product) throws Exception;

    public void deleteProductById(Long id) throws Exception;

}
