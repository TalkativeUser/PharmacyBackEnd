package com.ecommerce.pharmacy.service.Impl;

import com.ecommerce.pharmacy.DAO.ProductRepository;
import com.ecommerce.pharmacy.Entity.Product;
import com.ecommerce.pharmacy.DTO.ProductDTO;
import com.ecommerce.pharmacy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product findProductById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new Exception("Product not found");
        }
        return product.get();
    }


    @Override
    public List<Product> getProductByCategoryId(Long categoryId)throws Exception {
        List<Product> products =productRepository.findByCategoryId(categoryId);
        if (products == null) {
            throw new Exception("No products found for this category");
        }
        return products;
    }


    @Override
    public Page<Product> findProductsWithPaginationAndSorting(int offset,  String field) {
        final int pageSize = 10;
        return productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }


                             /**              ADMIN SERVICE            */
    @Override
    public void postProduct(ProductDTO productDTO) {
        Product product =new Product();
        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        if(product.getPriceAfterDiscount() <= 0 ){
            product.setPriceAfterDiscount(product.getPrice());
        }else{
            product.setPriceAfterDiscount(product.getPriceAfterDiscount());
        }
        product.setImgUrl(productDTO.getImgUrl());
        product.setCategory(productDTO.getCategory());
        productRepository.save(product);
    }

    @Override
    public void postListOfProducts(List<ProductDTO> productDTOList) {
        for(ProductDTO product:productDTOList) {
            postProduct(product);
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        Optional<Product> validateProduct = productRepository.findById(id);
        if (!validateProduct.isPresent()) {
            throw new Exception("product not found");
        }
        validateProduct.get().setName(product.getName());
        validateProduct.get().setImgUrl(product.getImgUrl());
        validateProduct.get().setQuantity(product.getQuantity());
        validateProduct.get().setCategory(product.getCategory());
        validateProduct.get().setPrice(product.getPrice());
        if(product.getPriceAfterDiscount() <= 0 ){
            validateProduct.get().setPriceAfterDiscount(product.getPrice());
        }else{
            validateProduct.get().setPriceAfterDiscount(product.getPriceAfterDiscount());
        }
        return validateProduct.get();
    }

    @Override
    public void deleteProductById(Long id) throws Exception {
        Optional<Product> validateProduct = productRepository.findById(id);
        if (!validateProduct.isPresent()) {
            throw new Exception("Product not found");
        }
        productRepository.deleteById(id);
    }
}
